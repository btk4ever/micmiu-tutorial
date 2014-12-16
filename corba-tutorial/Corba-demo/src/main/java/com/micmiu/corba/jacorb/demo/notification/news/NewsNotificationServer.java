package com.micmiu.corba.jacorb.demo.notification.news;

import java.util.Properties;
import java.util.Random;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNotification.DefaultPriority;
import org.omg.CosNotification.EventHeader;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.FixedEventHeader;
import org.omg.CosNotification.Priority;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;
import org.omg.CosNotifyChannelAdmin.*;
import org.omg.CosNotifyComm.InvalidEventType;
import org.omg.CosNotifyComm.StructuredPushSupplierHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * Notification News Demo Server
 * 1. 启动NameService        服务: $JACORB_HOME/bin/ns -DOAPort=1234
 * 2. 启动NotificationService服务: $JACORB_HOME/bin/nfty  -port 4321 -printCorbaloc
 * 3. 启动运行改程序 + 2个参数 如下:
 * -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * -ORBInitRef NotificationService=corbaloc::127.0.0.1:4321/NotificationService
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 16:30
 */
public class NewsNotificationServer implements Runnable {

	private ORB orb;
	private StructuredProxyPushConsumer structuredProxyPushConsumer;

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		props.put("org.omg.PortableInterceptor.ORBInitializerClass.bidir_init",
				"org.jacorb.orb.giop.BiDirConnectionInitializer");

		try {
			ORB orb = ORB.init(args, props);
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();

			NamingContextExt ncExt = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			EventChannelFactory eventChannelFactory = EventChannelFactoryHelper.narrow(orb.resolve_initial_references("NotificationService"));

			Property[] initialQos = new Property[0];
			Property[] initialAdmin = new Property[0];
			EventChannel eventChannel = eventChannelFactory.create_channel(initialQos, initialAdmin, new IntHolder());

			ncExt.rebind(ncExt.to_name("Notification.News"), eventChannel);

			StructuredPushSupplierImpl pushSupplierImpl = new StructuredPushSupplierImpl();
			SupplierAdmin supplierAdmin = eventChannel.default_supplier_admin();
			ProxyConsumer proxyConsumer = supplierAdmin.obtain_notification_push_consumer(ClientType.STRUCTURED_EVENT, new IntHolder());
			StructuredProxyPushConsumer proxyPushConsumer = StructuredProxyPushConsumerHelper.narrow(proxyConsumer);
			proxyPushConsumer.connect_structured_push_supplier(
					StructuredPushSupplierHelper.narrow(rootPoa.servant_to_reference(pushSupplierImpl)));

			Thread t = new Thread(new NewsNotificationServer(orb, proxyPushConsumer));
			t.start();

			orb.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NewsNotificationServer(ORB orb, StructuredProxyPushConsumer proxyPushConsumer) {
		this.orb = orb;
		this.structuredProxyPushConsumer = proxyPushConsumer;
	}

	public void run() {
		int i = 0;
		try {
			this.structuredProxyPushConsumer.offer_change(
					new EventType[]{new EventType("News", "Hadoop"),
							new EventType("News", "J2EE"),
							new EventType("News", "Spark")},
					new EventType[0]);

			while (!Thread.interrupted()) {
				EventType eventType = new EventType();
				eventType.domain_name = "News";
				eventType.type_name = (i % 3 == 0) ? "Hadoop" : ((i % 3 == 1) ? "J2EE" : "Spark");

				EventHeader eventHeader = new EventHeader();

				eventHeader.fixed_header = new FixedEventHeader();
				eventHeader.fixed_header.event_type = eventType;
				eventHeader.fixed_header.event_name = "Event No. " + i++;

				eventHeader.variable_header = new Property[1];
				eventHeader.variable_header[0] = new Property();
				eventHeader.variable_header[0].name = Priority.value;
				eventHeader.variable_header[0].value = orb.create_any();
				short priority = (short) (DefaultPriority.value + new Random().nextInt(3));
				eventHeader.variable_header[0].value.insert_short(priority);

				StructuredEvent event = new StructuredEvent();
				event.header = eventHeader;
				event.filterable_data = new Property[0];
				event.remainder_of_body = orb.create_any();
				event.remainder_of_body.insert_string(eventType.type_name + " News, Priority " + priority);

				this.structuredProxyPushConsumer.push_structured_event(event);
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Disconnected e) {
			e.printStackTrace();
		} catch (InvalidEventType e) {
			e.printStackTrace();
		}

		this.structuredProxyPushConsumer.disconnect_structured_push_consumer();
		this.orb.shutdown(true);
	}
}
