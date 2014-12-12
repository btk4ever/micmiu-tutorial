package com.micmiu.corba.jacorb.hello.server;

import com.micmiu.corba.jacorb.hello.HelloServicePOA;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/11/2014
 * Time: 10:45
 */
public class HelloServiceJacOrbImpl extends HelloServicePOA {

	@Override
	public String sayHello(String name) {
		System.out.println("[服务端] 接收的参数 : " + name);
		String ret = "Hi," + name + " welcome to CORBA(JacORB)";
		System.out.println("[服务端] 返回信息 : " + ret);
		return ret;
	}

}
