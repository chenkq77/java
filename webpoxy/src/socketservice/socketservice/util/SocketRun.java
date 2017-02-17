package socketservice.socketservice.util;

import java.io.IOException;

import socketservice.socketservice.util.connect.Server;

public class SocketRun implements Runnable{
	 public void run() {
		 try {
			Server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
 }