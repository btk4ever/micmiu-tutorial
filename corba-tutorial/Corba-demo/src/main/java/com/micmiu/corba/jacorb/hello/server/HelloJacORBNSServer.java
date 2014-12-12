package com.micmiu.corba.jacorb.hello.server;

import com.micmiu.corba.jacorb.hello.HelloService;
import com.micmiu.corba.jacorb.hello.HelloServiceHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

/**
 * JacORB NameService 服务端
 * 运行服务端先启动NS监听: $JACORB_HOME/bin/ns -DOAPort=1234
 * 运行服务端 + 参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/11/2014
 * Time: 10:49
 */
public class HelloJacORBNSServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
			props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");

			// 创建和初始化ORB
			ORB orb = ORB.init(args, props);

			// 获得根POA的引用，并且激活POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();


			// 创建一个HelloServiceJacOrbImpl对象的CORBA类型的对象引用
			HelloServiceJacOrbImpl helloServiceImpl = new HelloServiceJacOrbImpl();
			org.omg.CORBA.Object servantObj = rootpoa.servant_to_reference(helloServiceImpl);
			HelloService service = HelloServiceHelper.narrow(servantObj);

			// 把HelloService对象与HelloService名字绑定
			String name = "HelloService";
			org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
			NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			nc.rebind(nc.to_name(name), service);

			System.out.println("CORBA[JacORB] Server ready...");

			//等待客户端访问
			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
