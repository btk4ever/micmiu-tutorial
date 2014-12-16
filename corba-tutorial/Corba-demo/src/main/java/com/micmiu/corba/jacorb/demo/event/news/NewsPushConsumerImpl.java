package com.micmiu.corba.jacorb.demo.event.news;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushConsumerPOA;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 13:35
 */
public class NewsPushConsumerImpl extends PushConsumerPOA {

	private ORB orb;
	private ProxyPushSupplier proxyPushSupplier;
	private int eventCount = 0;

	public NewsPushConsumerImpl(ORB orb, ProxyPushSupplier proxyPushSupplier) {
		this.orb = orb;
		this.proxyPushSupplier = proxyPushSupplier;
	}

	public void disconnect_push_consumer() {
		System.out.println("[PushConsumer] >>>>disconnect_push_consumer");
	}

	public void push(Any event) throws Disconnected {
		System.out.println("[PushConsumer] >>>> event: string = " + event.extract_string());
		if (eventCount++ > 10) {
			proxyPushSupplier.disconnect_push_supplier();
			orb.shutdown(false);
		}
	}
}
