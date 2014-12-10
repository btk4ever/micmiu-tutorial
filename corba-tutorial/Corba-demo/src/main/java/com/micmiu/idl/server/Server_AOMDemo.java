package com.micmiu.idl.server;

import com.micmiu.idl.hello.HelloService;
import com.micmiu.idl.hello.HelloServiceHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * 测试
 */
public class Server_AOMDemo {

	public static void main(String[] args) {

		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.openorb.orb.core.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.openorb.orb.core.ORBSingleton");

		try {
			// Initialize the ORB.
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);

			// 获取根POA引用
			POA poaRoot = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			// 激活POA管理器
			poaRoot.the_POAManager().activate();
			// 实例化一个MyServiceServerImpl对象
			HelloServiceServerImpl serviceImpl = new HelloServiceServerImpl();
			// 从servant获得一个对象引用
			org.omg.CORBA.Object refObj = poaRoot.servant_to_reference(serviceImpl);
			// 获得对象接口引用
			HelloService service = HelloServiceHelper.narrow(refObj);
			// 命名上下文
			org.omg.CORBA.Object ncObj = orb.resolve_initial_references("NameService");
			NamingContextExt nc = NamingContextExtHelper.narrow(ncObj);
			// 绑定一个服务引用,以便客户端可以调用
			nc.rebind(nc.to_name("MyServerObject"), service);

			System.out.println("MyService is running and waiting......");
			// 运行ORB
			orb.run();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
