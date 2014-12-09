package com.micmiu.corba.javaidl.client;
/*
 * The client implementation is generated by the ORB Studio.
 */

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Properties;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

class HelloServiceClientImpl {
	private com.micmiu.corba.javaidl.hello.HelloService target = null;
	private org.omg.CORBA.ORB orb = null;

	/**
	 * Constructor for HelloServiceClientImpl
	 *
	 * @throws Exception
	 */
	public HelloServiceClientImpl() throws Exception {
		initORB(null);
	}

	/**
	 * Constructor for HelloServiceClientImpl
	 *
	 * @throws Exception
	 * @see java.lang.Object#Object()
	 */
	public HelloServiceClientImpl(String[] args) throws Exception {
		initORB(args);
	}

	/**
	 * Initialize ORB.
	 *
	 * @param args
	 * @throws Exception
	 */
	public void initORB(String[] args) throws Exception {
		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "com.sun.corba.se.internal.POA.POAORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "com.sun.corba.se.internal.corba.ORBSingleton");

		// Initialize the ORB
		orb = org.omg.CORBA.ORB.init((String[]) args, props);

		// ---- Uncomment below to enable Naming Service access. ----
		//命名服务
		org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
		NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
		org.omg.CORBA.Object obj = nc.resolve_str("MyServerObject");

		// IOR服务
//		LineNumberReader input = new LineNumberReader(new FileReader("hello-javaidl-server.ior"));
//		String ior = input.readLine();
//		org.omg.CORBA.Object obj = orb.string_to_object(ior);

		target = com.micmiu.corba.javaidl.hello.HelloServiceHelper.narrow(obj);
	}

	/**
	 * Obtain ORB Interface.
	 *
	 * @return
	 */
	public com.micmiu.corba.javaidl.hello.HelloService getORBInterface() {
		return target;
	}

	/**
	 * Shutdown ORB.
	 */
	public void shutdown() {
		orb.shutdown(true);
	}

	/**
	 * Test driver for HelloServiceClientImpl.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 命名服务访问 +参数 -ORBInitialHost 127.0.0.1 -ORBInitialPort 12345

			HelloServiceClientImpl test = new HelloServiceClientImpl(args);

			// test.getORBInterface().operation1("A message in the bottle...");
			String ret = test.getORBInterface().sayHello("micmiu.com");
			System.out.println("[客户端] 调用结果 : " + ret);
			test.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
