package com.micmiu.corba.jacorb.demo.notification.news;

import java.util.Properties;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CORBA.UserException;
import org.omg.CosNotification.EventType;
import org.omg.CosNotifyChannelAdmin.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNotifyComm.StructuredPushConsumerHelper;
import org.omg.CosNotifyFilter.ConstraintExp;
import org.omg.CosNotifyFilter.Filter;
import org.omg.CosNotifyFilter.FilterFactory;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * EventChannel News Demo Client
 * 1. 启动NameService        服务: $JACORB_HOME/bin/ns -DOAPort=1234
 * 2. 启动NotificationService服务: $JACORB_HOME/bin/jaco  -port 4321 -printCorbaloc
 * 3. 启动运行改程序 + 2个参数 如下:
 * -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * -ORBInitRef NotificationService=corbaloc::127.0.0.1:4321/NotificationService
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 16:33
 */
public class NewsNotificationClient {

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
			props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
			props.put("org.omg.PortableInterceptor.ORBInitializerClass.bidir_init",
					"org.jacorb.orb.giop.BiDirConnectionInitializer");

			ORB orb = ORB.init(args, props);
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();

			NamingContextExt ncExt = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			EventChannel eventChannel = EventChannelHelper.narrow(ncExt.resolve_str("Notification.News"));

			createConsumerAllEvents(eventChannel, rootPoa);
			createConsumerHadoopPriority1Events(eventChannel, rootPoa);

			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createConsumerAllEvents(EventChannel eventChannel, POA rootPoa)
			throws UserException {
		StructuredPushConsumerImpl pushConsumerImpl = new StructuredPushConsumerImpl("CosumerAllEvents");
		ConsumerAdmin consumerAdmin = eventChannel.default_consumer_admin();
		ProxySupplier proxySupplier = consumerAdmin.obtain_notification_push_supplier(
				ClientType.STRUCTURED_EVENT, new IntHolder());
		StructuredProxyPushSupplier proxyPushSupplier = StructuredProxyPushSupplierHelper.narrow(proxySupplier);

		FilterFactory filterFactory = eventChannel.default_filter_factory();

		//Within a filter, individual constraints are combined using OR semantics.
		//When multiple filters apply, OR is applied to the combination of results of filters.
		ConstraintExp constraint1 = new ConstraintExp(
				new EventType[]{new EventType("News", "Hadoop")}, "TRUE");
		ConstraintExp constraint2 = new ConstraintExp(
				new EventType[]{new EventType("News", "J2EE")}, "TRUE");
		Filter filter1 = filterFactory.create_filter("EXTENDED_TCL");
		filter1.add_constraints(new ConstraintExp[]{constraint1, constraint2});

		ConstraintExp constraint3 = new ConstraintExp(
				new EventType[]{new EventType("News", "Spark")}, "TRUE");
		Filter filter2 = filterFactory.create_filter("EXTENDED_TCL");
		filter2.add_constraints(new ConstraintExp[]{constraint3});

		proxyPushSupplier.add_filter(filter1);
		proxyPushSupplier.add_filter(filter2);

		proxyPushSupplier.connect_structured_push_consumer(
				StructuredPushConsumerHelper.narrow(rootPoa.servant_to_reference(pushConsumerImpl)));
	}

	public static void createConsumerHadoopPriority1Events(EventChannel eventChannel, POA rootPoa)
			throws UserException {
		StructuredPushConsumerImpl pushConsumerImpl = new StructuredPushConsumerImpl("CosumerHadoopEvent");
		//When the results of filters associated with a proxy
		//and those of filters associated with its admin object are combined,
		//the actual Boolean operator depends on
		//the value of the MyOperator attribute in the admin object.
		ConsumerAdmin consumerAdmin = eventChannel.new_for_consumers(
				InterFilterGroupOperator.AND_OP, new IntHolder());
		System.out.println(consumerAdmin.MyOperator());
		StructuredProxyPushSupplier proxyPushSupplier =
				StructuredProxyPushSupplierHelper.narrow(
						consumerAdmin.obtain_notification_push_supplier(ClientType.STRUCTURED_EVENT, new IntHolder()));

		FilterFactory filterFactory = eventChannel.default_filter_factory();

		ConstraintExp constraint1 = new ConstraintExp(
				new EventType[]{new EventType("News", "Hadoop")}, "$Priority<2");
		Filter filter1 = filterFactory.create_filter("EXTENDED_TCL");
		filter1.add_constraints(new ConstraintExp[]{constraint1});
		consumerAdmin.add_filter(filter1);

		ConstraintExp constraint2 = new ConstraintExp(
				new EventType[]{new EventType("News", "Hadoop")}, "$.header.variable_header(Priority)>0");
		Filter filter2 = filterFactory.create_filter("EXTENDED_TCL");
		filter2.add_constraints(new ConstraintExp[]{constraint2});
		proxyPushSupplier.add_filter(filter2);

		proxyPushSupplier.connect_structured_push_consumer(
				StructuredPushConsumerHelper.narrow(rootPoa.servant_to_reference(pushConsumerImpl)));

		EventType[] eventTypes = proxyPushSupplier.obtain_offered_types(ObtainInfoMode.ALL_NOW_UPDATES_OFF);
		for (EventType eventType : eventTypes) {
			System.out.println("[Cosumer] <HadoopEvent> Type =:" + eventType.domain_name + "." + eventType.type_name);
		}
	}
}
