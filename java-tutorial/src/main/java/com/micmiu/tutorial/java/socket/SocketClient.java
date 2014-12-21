package com.micmiu.tutorial.java.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * java socket client
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-10-22 上午11:02:04
 * @version 1.0
 */
public class SocketClient {

	public static void main(String args[]) throws Exception {
		String host = "127.0.0.1";
		int port = 12345;

		Socket socket = null;
		try {
			socket = new Socket(host, port);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String line = null;
			while (null != (line = br.readLine())) {
				// 输出 >> Server
				os.println(line);
				// 刷新输出流，使Server立刻收到消息
				os.flush();
				if ("bye".equals(line)) {
					System.out.println("Client will stop.");
					break;
				}
				System.out.println("[Client say] >" + line);
				// 读取服务端信息
				System.out.println("[Server say] >" + is.readLine());

			}

			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
}
