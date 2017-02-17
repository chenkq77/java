package socketservice.socketservice.util.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.jtv.server.IGwServiceProxy;

import net.sf.json.JSONObject;

public class Server {
	  private static final Logger LOG = Logger.getLogger(Server.class);

	public static void main(String args[]) throws IOException {
		startServer();
	}

	public static void startServer() throws IOException {
		// 为了简单起见，所有的异常信息都往外抛
		int port = 24480;
		// 定义一个ServerSocket监听在端口8899上
		ServerSocket server = new ServerSocket(port);
		while (true) {
			// server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
			Socket socket = server.accept();
			// 每接收到一个Socket就建立一个新的线程来处理它
			new Thread(new Task(socket)).start();
		}
	}

	/**
	 * 用来处理Socket请求的
	 */
	static class Task implements Runnable {

		private Socket socket;

		public Task(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				handleSocket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 跟客户端Socket进行通信
		 * 
		 * @throws Exception
		 */
		private void handleSocket() throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String temp;
			int index;
			while ((temp = br.readLine()) != null) {
				System.out.println(temp);
				if ((index = temp.indexOf("-1")) != -1) {// 遇到-1时就结束接收
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			System.out.println("客户端: " + sb);
			LOG.error(sb);
			// 读完后写一句
			Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			writer.write("end\n");
			writer.flush();
			writer.close();
			br.close();
			socket.close();
			// 内外网传输
			IGwServiceProxy ig = new IGwServiceProxy();
			JSONObject json = new JSONObject();
			json.put("data", sb.toString());
			System.out.println("雨量数据传输内网："+json.toString());
			ig.publicInfo(json.toString(), "zzrain");
		}
	}
}
