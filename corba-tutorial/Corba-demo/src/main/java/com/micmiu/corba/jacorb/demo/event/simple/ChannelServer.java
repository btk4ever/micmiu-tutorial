package com.micmiu.corba.jacorb.demo.event.simple;


import org.jacorb.events.EventChannelImpl;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * JacORB EventChannel
 * 先启动NS监听: $JACORB_HOME/bin/ns -DOAPort=1234
 * 运行 + 参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * User: <a href="http://micmiu.com">micmiu</a>
 */
public class ChannelServer {

	private static String[] strings;

	static public void main(String[] argv) {

		// argv = new String[]{"-ORBInitRef", "NameService=corbaloc::127.0.0.1:1234/NameService"};
		Properties props = new Properties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		props.put("org.omg.PortableInterceptor.ORBInitializerClass.bidir_init", "org.jacorb.orb.giop.BiDirConnectionInitializer");

		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(argv, props);

			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			EventChannelImpl channel = new EventChannelImpl(orb, poa);

			poa.the_POAManager().activate();

			org.omg.CORBA.Object o = poa.servant_to_reference(channel);

			nc.bind(nc.to_name("event.simple"), o);

			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


