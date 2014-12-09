package com.micmiu.ejb2x;

import java.util.Properties;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ClientTest {
	private com.micmiu.ejb2x.HelloWorldRemote target = null;
	private org.omg.CORBA.ORB orb = null;

	/**
	 * Constructor for HelloServiceClientImpl
	 * 
	 * @throws Exception
	 */
	public ClientTest() throws Exception {
		initORB(null);
	}

	/**
	 * Constructor for HelloServiceClientImpl
	 * 
	 * @throws Exception
	 * @see java.lang.Object#Object()
	 */
	public ClientTest(String[] args) throws Exception {
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
		props.setProperty("org.omg.CORBA.ORBClass",
				"com.sun.corba.se.internal.POA.POAORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass",
				"com.sun.corba.se.internal.corba.ORBSingleton");

		// Initialize the ORB
		orb = org.omg.CORBA.ORB.init((String[]) args, props);

		// ---- Uncomment below to enable Naming Service access. ----
		org.omg.CORBA.Object ncobj = orb
				.resolve_initial_references("NameService");
		NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
		org.omg.CORBA.Object obj = nc.resolve_str("MyServerObject");

		// LineNumberReader input = new LineNumberReader(new
		// FileReader("server.ior"));
		// String ior = input.readLine();
		// org.omg.CORBA.Object obj = orb.string_to_object(ior);

		target = com.micmiu.ejb2x.HelloWorldRemoteHelper.narrow(obj);
	}

	/**
	 * Obtain ORB Interface.
	 * 
	 * @return
	 */
	public com.micmiu.ejb2x.HelloWorldRemote getORBInterface() {
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
			ClientTest test = new ClientTest(args);

			// test.getORBInterface().operation1("A message in the bottle...");
			String ret = test.getORBInterface().sayHello("micmiu.com");
			System.out.println("[客户端] 调用结果 : " + ret);
			test.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
