package com.micmiu.corba.jacorb.demo.event.simple;

/**
 * @authors Joerg v. Frantzius, Rainer Lischetzki, Gerald Brose 1997
 *
 * A simple demo for using the event channel as a push consumer
 * of events. This consumer unregisters and quits after receiving
 * 5 events.
 *
 */

import org.omg.CosEventChannelAdmin.*;
import org.omg.CosEventComm.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * EventChannel  Simple Demo Push Consumer
 * 1. 启动NS监听: $JACORB_HOME/bin/ns -DOAPort=1234
 * 2. 启动信道服务 ChannelServer
 * 3. 启动 PushConsumer  PushSupplier
 * 4. 运行该程序 + 参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 */
public class PushConsumerDemo implements PushConsumerOperations {
	private short count = 0;
	private ProxyPushSupplier myPps = null;
	private int limit = 25;

	static org.omg.CORBA.ORB orb = null;

	public PushConsumerDemo(ProxyPushSupplier _pps) {
		myPps = _pps;
	}

	public void disconnect_push_consumer() {
		System.out.println("[PushConsumer] >>>> disconnect_push_consumer.");
	}

	public static void main(String[] args) {
		EventChannel ecs = null;
		ConsumerAdmin ca = null;
		PushConsumer pushConsumer = null;
		ProxyPushSupplier pps = null;

		try {
			orb = org.omg.CORBA.ORB.init(args, null);
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			ecs = EventChannelHelper.narrow(nc.resolve(nc.to_name("event.simple")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		ca = ecs.for_consumers();
		pps = ca.obtain_push_supplier();

		try {
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();

			PushConsumerPOATie pt = new PushConsumerPOATie(new PushConsumerDemo(pps));
			pt._this_object(orb);
			pushConsumer = PushConsumerHelper.narrow(rootPOA.servant_to_reference(pt));
			pps.connect_push_consumer(pushConsumer);
			System.out.println("[PushConsumer] >>>> PushConsumerDemo registered.");
			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[PushConsumer] >>>> Quit.");
	}

	public synchronized void push(org.omg.CORBA.Any data) throws Disconnected {
		count++;
		System.out.println("[PushConsumer] >>>> event " + count + " : " + data.extract_string());
		if (count >= limit) {
			System.out.println("[PushConsumer] >>>> unregister");
			myPps.disconnect_push_supplier();
			// System.exit(0);
			orb.shutdown(false);
		}
	}
}