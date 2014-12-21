package com.micmiu.tutorial.java.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * java socket server
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-10-22 下午1:33:38
 * @version 1.0
 */
public class SocketServer {

	public static void main(String args[]) throws Exception {
		ServerSocket server = null;
		try {
			// 创建一个ServerSocket用来监听客户请求
			server = new ServerSocket(12345);
			System.out.println("Socket 服务已启动，监听端口< 12345 >...");

			Socket socket = null;
			// 使用accept()阻塞等待客户请求，有客户
			// 请求到来则产生一个Socket对象，并继续执行
			socket = server.accept();
			System.out.println("==============================\n正在处理请求...");
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String line = null;
			while (null != (line = br.readLine())) {
				// 向客户端输出该字符串
				os.println(line);
				os.flush();
				// 刷新输出流，使Client马上收到该字符串
				if ("bye".equals(line)) {
					System.out.println("Server will stop.");
					break;
				}
				System.out.println("[Server say] >" + line);
				System.out.println("[Client say] >" + is.readLine());
			}
			System.out.println("==============================");
			os.close();
			is.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error:" + e);

		} finally {
			if (null != server) {
				server.close();
			}
		}
	}
}
