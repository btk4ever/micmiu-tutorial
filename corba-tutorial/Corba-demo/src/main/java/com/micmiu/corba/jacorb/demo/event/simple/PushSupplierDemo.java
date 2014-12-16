package com.micmiu.corba.jacorb.demo.event.simple;

/**
 * @authors Joerg v. Frantzius, Rainer Lischetzki, Gerald Brose 1997
 *
 * A simple demo for using the event channel as a push supplier of events.
 *
 */

import org.omg.CosEventChannelAdmin.*;
import org.omg.CosEventComm.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.Any;
import org.omg.PortableServer.POA;

import java.util.Properties;

/**
 * EventChannel  Simple Demo Push Supplier
 * 1. 启动NS监听: $JACORB_HOME/bin/ns -DOAPort=1234
 * 2. 启动信道服务 ChannelServer
 * 3. 启动 PushConsumer  PushSupplier
 * 4. 运行该程序 + 参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 */
public class PushSupplierDemo extends PushSupplierPOA {

	public PushSupplierDemo(String[] args) {
		EventChannel ec = null;

		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);

		try {
			POA poa = org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();

			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			ec = EventChannelHelper.narrow(nc.resolve(nc.to_name("event.simple")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SupplierAdmin supplierAdmin = ec.for_suppliers();
		ProxyPushConsumer proxyPushConsumer = supplierAdmin.obtain_push_consumer();

		try {
			proxyPushConsumer.connect_push_supplier(_this(orb));
		} catch (AlreadyConnected ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < 30; i++) {
			try {
				Any any = orb.create_any();
				any.insert_string("Welcome to micmiu.com, your No:" + i);
				proxyPushConsumer.push(any);
				System.out.println("[PushSupplier] >>>> Pushing event # " + (i));
			} catch (Disconnected d) {
				d.printStackTrace();
			}
		}
		proxyPushConsumer.disconnect_push_consumer();
	}

	public void disconnect_push_supplier() {

		System.out.println("[PushSupplier] >>>> Push Supplier disconnected");
	}

	public static void main(String[] args) {

		new PushSupplierDemo(args);
	}
}
