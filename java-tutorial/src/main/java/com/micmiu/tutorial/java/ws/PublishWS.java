package com.micmiu.tutorial.java.ws;

import javax.xml.ws.Endpoint;

public class PublishWS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hello hello = new HelloImpl();
		Endpoint.publish("http://localhost:8088/hello", hello);

	}
}
