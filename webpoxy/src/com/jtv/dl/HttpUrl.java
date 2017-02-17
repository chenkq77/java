package com.jtv.dl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUrl {

	public static InputStream getContext(String url, Map<String, String> map) {
		HttpClient httpclient = new HttpClient();
		PostMethod postMethod = new UTF8PostMethod(url);
		InputStream responseBody = null;
		try {
			NameValuePair[] data = new NameValuePair[map.size()];
			int i = 0;
			for (String key : map.keySet()) {
				data[i] = new NameValuePair(key, map.get(key));
				i++;
			}
			postMethod.setRequestBody(data);
			postMethod.getParams().setParameter("http.protocol.cookie-policy",
					CookiePolicy.BROWSER_COMPATIBILITY);
			int statusCode1 = httpclient.executeMethod(postMethod);
			if (statusCode1 != HttpStatus.SC_OK) {
				System.out.println("Method is wrong " + postMethod.getStatusLine());
				return null;
			}
			 responseBody = postMethod.getResponseBodyAsStream();
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("the line is wrong!");
			e.printStackTrace();
		} 
		return responseBody;
	}

	// Inner class for UTF-8 support
	public static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
			super(url);
		}

		@Override
		public String getRequestCharSet() {
			// return super.getRequestCharSet();
			return "UTF-8";
		}
	}
	
	public static final double reqidToVerifyId(String var0) {
        char[] var1 = var0.toCharArray();
        int var2 = a(var1, 0);
        int var3 = a(var1, 8);
        int var4 = a(var1, 16);
        int var5 = a(var1, 24);
        return Math.abs((double)var2 * 1.1977D + (double)var3 * 1.1103D - (double)var4 * 3.4D + (double)var5 * 2.1034D);
    }

    public static final String createRequestVerify(String var0) {
        StringBuffer var1 = new StringBuffer();
        String var2 = UUID.randomUUID().toString();
        var1.append("myreq");
        var1.append("=");
        var1.append(var2);
        var1.append(var0);
        var1.append("myver");
        var1.append("=");
        var1.append(reqidToVerifyId(var2));
        return var1.toString();
    }

    private static final int a(char[] var0, int var1) {
        return var0[var1] + var0[var1 + 1] - var0[var1 + 2] - var0[var1 + 3] + var0[var1 + 4] + var0[var1 + 5] + var0[var1 + 6] - var0[var1 + 7];
    }
    
    
    private static byte[] getImg(String strUrl) throws IOException{
    	String strUrl1 = "http://10.16.4.189:8092/iserver/services/map-gwrail/rest/maps/%E5%B7%A5%E5%8A%A1%E5%9C%B0%E5%9B%BE.jsonp?callback=SuperMap.Util.RequestJSONP.supermap_callbacks%5B1484096212904397%5D&sectionCount=1&sectionIndex=0&jsonpUserID=1484096212903";
    	//构造URL
    	URL url = new URL(strUrl1);
    	//构造连接
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	//这个网站要模拟浏览器才行
    	conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
    	//打开连接
    	conn.connect();
    	//打开这个网站的输入流
    	InputStream inStream = conn.getInputStream();
    	//用这个做中转站 ，把图片数据都放在了这里，再调用toByteArray()即可获得数据的byte数组
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    	//用这个是很好的，不用一次就把图片读到了文件中
    	//要是需要把图片用作其他用途呢？所以直接把图片的数据弄成一个变量，十分有用
    	//相当于操作这个变量就能操作图片了
    	byte [] buf = new byte[1024];
    	//为什么是1024？
    	//1024Byte=1KB，分配1KB的缓存
    	//这个就是循环读取，是一个临时空间，多大都没关系
    	//这没有什么大的关系，你就是用999这样的数字也没有问题，就是每次读取的最大字节数。
    	//byte[]的大小，说明你一次操作最大字节是多少
    	//虽然读的是9M的文件，其实你的内存只用1M来处理，节省了很多空间．
    	//当然，设得小，说明I/O操作会比较频繁，I/O操作耗时比较长，
    	//这多少会有点性能上的影响．这看你是想用空间换时间，还是想用时间换空间了．
    	//时间慢总比内存溢出程序崩溃强．如果内存足够的话，我会考虑设大点．
    	int len = 0;
    	//读取图片数据
    	while((len=inStream.read(buf))!=-1){
    	outStream.write(buf,0,len);
    	}
    	inStream.close();
    	outStream.close();
    	return outStream.toByteArray();
    	
    }
    public static void main(String[] args) throws IOException {
    	String name=java.net.URLEncoder.encode("http://10.16.4.189:8092/iserver/services/map-gwrail/rest/maps/工务地图.jsonp", "UTF-8");
    	System.out.println(name);
//    	System.out.println(getImg(""));
	}

}
