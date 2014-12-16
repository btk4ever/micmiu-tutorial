package com.micmiu.corba.jacorb.demo.notification.office;


import org.omg.CosNotification.*;
import org.omg.CosNotifyComm.*;
import org.omg.CosNotifyChannelAdmin.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.*;

import demo.notification.office.*;

import java.util.Properties;

/**
 * Notification Office Demo Server
 * 1. 启动NameService        服务: $JACORB_HOME/bin/ns -DOAPort=1234
 * 2. 启动NotificationService服务: $JACORB_HOME/bin/nfty  -port 4321 -printCorbaloc
 * 3. 启动此服务端 + 2个参数 如下:
 * -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * -ORBInitRef NotificationService=corbaloc::127.0.0.1:4321/NotificationService
 */
public class PrintServer {

	/**
	 * main
	 */
	public static void main(String argv[]) {

		Properties props = new Properties();
//		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
//		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
//		props.put("org.omg.PortableInterceptor.ORBInitializerClass.bidir_init",
//				"org.jacorb.orb.giop.BiDirConnectionInitializer");

		EventChannel channel = null;
		ORB orb = ORB.init(argv, props);

		try {
			// initialize POA, get naming and event service references
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();

			NamingContextExt nc =
					NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			EventChannelFactory factory =
					EventChannelFactoryHelper.narrow(orb.resolve_initial_references("NotificationService"));

			if (factory == null) {
				System.err.println("Could not find or narrow EventChannelFactory");
				System.exit(1);
			}

			org.omg.CORBA.IntHolder idHolder = new org.omg.CORBA.IntHolder();

			Property[] qos = new Property[0];
			Property[] adm = new Property[0];

			channel = factory.create_channel(qos, adm, idHolder);
			nc.rebind(nc.to_name("notification.office"), channel);

			System.out.println("Channel " + idHolder.value +
					" created and bound to name [notification.office].");

			// create a Printer object, implicitly activate it and advertise its presence
			PrinterImpl printer = new PrinterImpl(channel, orb, poa);
			printer.connect();
			System.out.println("Printer created and connected");

			org.omg.CORBA.Object printerObj = poa.servant_to_reference(printer);
			nc.rebind(nc.to_name("Office.Printer"), printerObj);
			System.out.println("name [Office.Printer] exported");

			// wait for requests
			orb.run();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
