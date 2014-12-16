package com.micmiu.corba.jacorb.demo.event.news;

import org.omg.CORBA.ORB;
import org.omg.CosEventChannelAdmin.ConsumerAdmin;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.CosEventChannelAdmin.EventChannelHelper;
import org.omg.CosEventChannelAdmin.ProxyPushSupplier;
import org.omg.CosEventComm.PushConsumer;
import org.omg.CosEventComm.PushConsumerHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * EventChannel Client for News
 * 运行事件信道 先启动NS监听: $JACORB_HOME/bin/ns -DOAPort=1234 和 服务端程序
 * 启动运行 + 参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/15/2014
 * Time: 13:36
 */
public class NewsClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Properties props = new Properties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		props.put("org.omg.PortableInterceptor.ORBInitializerClass.bidir_init", "org.jacorb.orb.giop.BiDirConnectionInitializer");
		try {

			ORB orb = ORB.init(args, props);
			NamingContextExt ncExt = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();

			EventChannel eventChannel = EventChannelHelper.narrow(ncExt.resolve_str("event.news"));
			ConsumerAdmin consumerAdmin = eventChannel.for_consumers();
			ProxyPushSupplier proxyPushSupplier = consumerAdmin.obtain_push_supplier();

			NewsPushConsumerImpl pushConsumerImpl = new NewsPushConsumerImpl(orb, proxyPushSupplier);
			PushConsumer pushConsumer = PushConsumerHelper.narrow(rootPoa.servant_to_reference(pushConsumerImpl));
			proxyPushSupplier.connect_push_consumer(pushConsumer);

			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

