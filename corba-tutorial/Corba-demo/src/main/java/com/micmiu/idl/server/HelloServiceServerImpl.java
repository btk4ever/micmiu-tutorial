package com.micmiu.idl.server;
/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 */
public class HelloServiceServerImpl extends com.micmiu.idl.hello.HelloServicePOA {
	/**
	 * Constructor for HelloServiceServerImpl 
	 */
	public HelloServiceServerImpl() {
	}
	@Override
	public String sayHello(String name) {
		System.out.println("[服务端] 接收的参数 : " + name);
		String ret = "Hi," + name + " welcome to CORBA";
		System.out.println("[服务端] 返回信息 : " + ret);
		return ret;
	}
}
