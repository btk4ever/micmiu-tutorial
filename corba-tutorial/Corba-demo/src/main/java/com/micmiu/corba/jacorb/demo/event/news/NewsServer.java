package com.micmiu.corba.jacorb.demo.event.news;

import org.jacorb.events.EventChannelImpl;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CosEventChannelAdmin.ProxyPushConsumer;
import org.omg.CosEventChannelAdmin.SupplierAdmin;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosEventComm.PushSupplierHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * EventChannel Server for News
 * 运行事件信道 先启动NS监听: $JACORB_HOME/bin/ns -DOAPort=1234
 * 启动运行 + 参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 13:27
 */
public class NewsServer implements Runnable {

	private ORB orb;
	private ProxyPushConsumer proxyPushConsumer;

	public static void main(String[] args) {
//		args = new String[]{"-ORBInitRef", "NameService=corbaloc::127.0.0.1:1234/NameService"};
		Properties props = new Properties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		props.put("org.omg.PortableInterceptor.ORBInitializerClass.bidir_init", "org.jacorb.orb.giop.BiDirConnectionInitializer");
		try {

			ORB orb = ORB.init(args, props);
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();
			NamingContextExt ncExt = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Create event channel
			EventChannelImpl eventChannel = new EventChannelImpl(orb, rootPoa);
			ncExt.rebind(ncExt.to_name("event.news"), rootPoa.servant_to_reference(eventChannel));

			// Obtain the push consumer proxy object
			NewsPushSupplierImpl pushSupplierImpl = new NewsPushSupplierImpl();
			SupplierAdmin supplierAdmin = eventChannel.for_suppliers();
			ProxyPushConsumer proxyPushConsumer = supplierAdmin.obtain_push_consumer();
			proxyPushConsumer.connect_push_supplier(PushSupplierHelper.narrow(rootPoa.servant_to_reference(pushSupplierImpl)));

			Thread t = new Thread(new NewsServer(orb, proxyPushConsumer));
			t.setDaemon(true);
			t.start();

			orb.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NewsServer(ORB orb, ProxyPushConsumer proxyPushConsumer) {
		this.orb = orb;
		this.proxyPushConsumer = proxyPushConsumer;
	}

	public void run() {
		int i = 0;

		try {
			while (!Thread.interrupted()) {
				Any event = this.orb.create_any();
				event.insert_string("wellcome to micmiu.com, your No is:" + i++);
				//event.insert_long(i++);
				this.proxyPushConsumer.push(event);
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Disconnected e) {
			e.printStackTrace();
		}

		this.proxyPushConsumer.disconnect_push_consumer();
		this.orb.shutdown(true);
	}
}