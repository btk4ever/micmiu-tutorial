package com.micmiu.corba.jacorb.demo.event.simple;


import org.omg.CosNaming.*;
import org.omg.CosEventChannelAdmin.*;
import org.omg.CosEventComm.*;
import org.omg.CORBA.Any;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * 测试客户端之前需要 启动监听 和服务端程序
 * 运行需要 +参数  -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 */
public class PullConsumerDemo extends PullConsumerPOA {

	public PullConsumerDemo(org.omg.CORBA.ORB orb) {
		_this_object(orb);
	}

	public void disconnect_pull_consumer() {
		System.out.println("Consumer disconnected");
	}

	public static void main(String argv[]) {
		EventChannel ecs = null;
		ConsumerAdmin ca;
		ProxyPullSupplier pps;
		PullConsumer pullConsumer;
		Any received = null;

		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(argv, props);

		// binding the event channel reference
		try {
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

			poa.the_POAManager().activate();
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			ecs = EventChannelHelper.narrow(nc.resolve(nc.to_name("event.simple")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// registering ourselves

		pullConsumer = (PullConsumer) (new PullConsumerDemo(orb))._this();
		ca = ecs.for_consumers();
		pps = ca.obtain_pull_supplier();
		try {
			pps.connect_pull_consumer((PullConsumer) pullConsumer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("TestPullConsumer registered.");

		// pulling events
		int i = 0;
		while (i < 10) {
			System.out.println("pulling event " + i);
			org.omg.CORBA.BooleanHolder bh = new org.omg.CORBA.BooleanHolder();
			try {
				received = pps.try_pull(bh);
				//		received = pps.pull();
				if (bh.value) {
					System.out.println("received " + (i++) + " : " +
							received.extract_string());
				} else {
					// we did not get any real any, so we continue
					// polling after a short nap
					Thread.currentThread().sleep(2000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pps.disconnect_pull_supplier();
	}
}


