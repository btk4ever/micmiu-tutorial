package com.micmiu.corba.openorb.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.IdAssignmentPolicyValue;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.ThreadPolicyValue;

public class Server_AOM {

	/**
	 * OpenORB 模式
	 * 命名服务运行之前需要先监听服务: %OPENORB_HOME%/NamingService/bin/ins -ORBPort=1234
	 * 然后启动服务端: +参数 -ORBInitRef NameService=corbaloc::1.2@127.0.0.1:1234/NameService
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		Properties props = System.getProperties();
		//props.setProperty("org.omg.CORBA.ORBClass", "org.openorb.CORBA.ORB");
		//props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.openorb.CORBA.ORBSingleton");
		// OpenORB 1.4.X
		props.setProperty("org.omg.CORBA.ORBClass", "org.openorb.orb.core.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.openorb.orb.core.ORBSingleton");

		try {
			// Initialize the ORB.
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);

			// get a reference to the root POA
			org.omg.CORBA.Object obj = orb.resolve_initial_references("RootPOA");
			POA poaRoot = POAHelper.narrow(obj);

			// Create policies for our persistent POA
			org.omg.CORBA.Policy[] policies = {
					poaRoot.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
					poaRoot.create_id_assignment_policy(IdAssignmentPolicyValue.USER_ID),
					poaRoot.create_thread_policy(ThreadPolicyValue.ORB_CTRL_MODEL)
			};

			// Create myPOA with the right policies
			POA poa = poaRoot.create_POA("HelloServiceAOMServerImpl_poa", poaRoot.the_POAManager(), policies);

			// Create the servant
			HelloServiceAOMServerImpl servant = new HelloServiceAOMServerImpl();

			// Activate the servant with the ID on myPOA
			byte[] objectId = "AnyObjectID".getBytes();
			poa.activate_object_with_id(objectId, servant);

			// Activate the POA manager
			poaRoot.the_POAManager().activate();

			// Get a reference to the servant and write it down.
			obj = poa.servant_to_reference(servant);

			// ---- Uncomment below to enable Naming Service access. ----
			// 命名服务
			org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
			NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			nc.bind(nc.to_name("MyServerObject"), obj);

			//IOR 服务
//			PrintWriter ps = new PrintWriter(new FileOutputStream(new File("ior/hello-openorb-server.ior")));
//			ps.println(orb.object_to_string(obj));
//			ps.close();

			System.out.println("CORBA Server ready...");

			// Wait for incoming requests
			orb.run();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
