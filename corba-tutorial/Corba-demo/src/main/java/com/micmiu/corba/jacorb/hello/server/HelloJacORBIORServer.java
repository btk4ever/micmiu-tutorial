package com.micmiu.corba.jacorb.hello.server;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * JacORB IOR 服务
 * <p/>
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/11/2014
 * Time: 10:49
 */
public class HelloJacORBIORServer {

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

			// 创建一个HelloServiceImpl对象的CORBA类型的对象引用
			HelloServiceJacOrbImpl helloServiceImpl = new HelloServiceJacOrbImpl();
			org.omg.CORBA.Object servantObj = rootpoa.servant_to_reference(helloServiceImpl);

			// IOR服务
			PrintWriter ps = new PrintWriter(new FileOutputStream(new File("ior/hello-jacorb-server.ior")));
			ps.println(orb.object_to_string(servantObj));
			ps.close();

			System.out.println("CORBA[JacORB] Server ready...");

			//等待客户端访问
			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
