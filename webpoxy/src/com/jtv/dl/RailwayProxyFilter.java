/**
 * 
 */
package com.jtv.dl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;

/**
 * 
 * @author ouzf
 */
public class RailwayProxyFilter implements Filter {
	private static String redireUrl = "";
	private static String proxyurl = "";
	private static String map = "";
	private static String urlmap = "";

	static {
		InputStream in = RailwayProxyFilter.class.getClassLoader().getResourceAsStream("config.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			redireUrl = p.getProperty("url");
			proxyurl = p.getProperty("proxyurl");
			map = p.getProperty("map");
			urlmap = p.getProperty("urlmap");
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest parm1, ServletResponse parm2, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) parm1;
		HttpServletResponse res = (HttpServletResponse) parm2;
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		Map<String, String> datas = new HashMap<String, String>();
		Enumeration enu = req.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			datas.put(paraName, req.getParameter(paraName));
		}
		String targetUrl = redireUrl + req.getRequestURI();
		if(req.getRequestURI().contains("gis")){
			targetUrl = urlmap + req.getRequestURI();
		}
		System.out.println(targetUrl);
		// header
		if (req.getHeader("myreq") != null) {
			String myver = "";
			if (targetUrl.indexOf('?') < 0) {
				myver = "?";
			} else {
				myver = "&";
			}
			myver = myver + "myreq=" + req.getHeader("myreq");
			myver = myver + "&myver=" + req.getHeader("myver");
			targetUrl = targetUrl + myver;
		}

		String jsessionid = null;
		if (null != req.getCookies()) {
			for (Cookie cookie : req.getCookies()) {
				if ("JSESSIONID".equals(cookie.getName())) {
					jsessionid = cookie.getValue();
				}
			}
		}
		if (jsessionid != null) {
			if (targetUrl.indexOf('?') > 0) {
				targetUrl = targetUrl.substring(0, targetUrl.indexOf('?')) + ";jsessionid=" + jsessionid
						+ targetUrl.substring(targetUrl.indexOf('?'));
			} else {
				targetUrl = targetUrl + ";jsessionid=" + jsessionid;
			}
		}
		if (req.getRequestURI().contains("iserver")) {
			targetUrl = map + req.getRequestURI();
		}
		datas.put("url", targetUrl);
		try {
			if (targetUrl.indexOf(".do") > 0) {
				datas.put("webtypeckq", "0");
				output(req, res, getPostResult(datas));
			} else {
				datas.put("webtypeckq", "1");
				byte[] b = getResult(datas);
				output(req, res, b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] getResult(Map<String, String> datas) throws Exception {
		String httpUrl = proxyurl;// "http://101.200.181.176:8087/zz/operation/asset/mc/Asset.do?method=getZzMethodData&type=URL&data=";
		httpUrl = httpUrl + "&data=" + JSONObject.fromObject(datas).toString();
		URL url = new java.net.URL(httpUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/4.0");
		connection.connect();
		InputStream in = connection.getInputStream();
		return Base64.decodeBase64(readBit(in));
	}

	private byte[] getPostResult(Map<String, String> datas) throws Exception {
		String httpUrl = proxyurl;// "http://101.200.181.176:8087/zz/operation/asset/mc/Asset.do?method=getZzMethodData&type=URL&data=";
		Map<String, String> map = new HashMap<String, String>();
		map.put("data", JSONObject.fromObject(datas).toString());
		InputStream in = HttpUrl.getContext(proxyurl, map);
		return Base64.decodeBase64(readBit(in));
	}

	private byte[] readBit(InputStream stream) throws Exception {
		BufferedInputStream in = new BufferedInputStream(stream);
		int readLen = -1;
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		byte[] b = new byte[8192];
		while ((readLen = in.read(b)) != -1) {
			buf.write(b, 0, readLen);
		}
		in.close();
		return buf.toByteArray();
	}

	private void output(HttpServletRequest req, HttpServletResponse response, byte[] bytes) throws Exception {
		response.reset();
		response.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		if (uri.indexOf('?') >= 0) {
			uri = uri.substring(0, uri.indexOf('?'));
		}
		if (uri.indexOf('.') > 0) {
			String mime = getMime.get(uri.substring(uri.indexOf('.')));
			response.setContentType(mime + ";charset=UTF-8");
		} else {
			response.setContentType("text/xml;charset=UTF-8");
		}
		response.setContentLength(bytes.length);
		OutputStream out = response.getOutputStream();
		out.write(bytes, 0, bytes.length);
		out.flush();
		out.close();
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("��ʼ��HTTP����...");
	}
}
