package com.micmiu.tutorial.java.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int port = 12345;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			Socket socket = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			while (true) {
				String str = in.readLine();

				if (str.equals("byebye")) {
					break;
				}
				System.out.println("In Server reveived the info: " + str);
				out.println(str);
				out.flush();
			}
			socket.close();
		} catch (Exception e) {

		} finally {
			server.close();
		}

	}

}
