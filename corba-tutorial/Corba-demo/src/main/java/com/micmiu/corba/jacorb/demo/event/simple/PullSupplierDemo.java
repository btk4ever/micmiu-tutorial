package com.micmiu.corba.jacorb.demo.event.simple;

/**
 * @authors Joerg v. Frantzius, Rainer Lischetzki, Gerald Brose 1997
 *
 * A simple demo for using the event channel as a push supplier of events.
 *
 */

import org.omg.CosNaming.*;
import org.omg.CosEventChannelAdmin.*;
import org.omg.CosEventComm.*;
import org.omg.CORBA.Any;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * Supplier 启动之前需要先 启动监听 和 EventChannel服务
 * 运行需要 +参数  -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 */
class PullSupplierDemo extends Thread implements PullSupplierOperations {
	Any event = null;

	public PullSupplierDemo() {
		start();
	}

	public void disconnect_pull_supplier() {
		System.out.println("Bye.");
	}

	static public void main(String argv[]) {
		EventChannel e = null;
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(argv, null);

		try {
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			e = EventChannelHelper.narrow(nc.resolve(nc.to_name("event.simple")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SupplierAdmin supplierAdmin = e.for_suppliers();
		ProxyPullConsumer proxyPullConsumer = supplierAdmin.obtain_pull_consumer();

		try {
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();

			PullSupplierPOATie pt = new PullSupplierPOATie(new PullSupplierDemo());
			pt._this_object(orb);
			org.omg.CORBA.Object o = poa.servant_to_reference(pt);

			proxyPullConsumer.connect_pull_supplier(PullSupplierHelper.narrow(o));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Any pull() throws Disconnected {
		System.out.println("I m being pulled.");
		event = org.omg.CORBA.ORB.init().create_any();
		event.insert_string("Pull.");
		return event;
	}

	public void run() {
		// do something
		while (true) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (Exception e) {
				disconnect_pull_supplier();
			}
		}
	}


	public Any try_pull(org.omg.CORBA.BooleanHolder has_event) throws Disconnected {
		System.out.println("I m being try_pulled.");
		event = org.omg.CORBA.ORB.init().create_any();
		event.insert_string("TryPull.");
		has_event.value = true;
		return event;
	}
}


