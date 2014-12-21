package com.micmiu.tutorial.java.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HelloImpl implements Hello {

	@Override
	@WebMethod
	public String sayHello(String name) {
		return "Hello, " + name;
	}

	@Override
	public String sayMsg() {
		return "welcom";
	}

	@WebMethod(exclude = true)
	public String test() {
		return "test";
	}

}
