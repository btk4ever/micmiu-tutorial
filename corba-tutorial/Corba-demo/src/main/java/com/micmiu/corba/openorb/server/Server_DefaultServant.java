package com.micmiu.corba.openorb.server;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.omg.PortableServer.IdAssignmentPolicyValue;
import org.omg.PortableServer.IdUniquenessPolicyValue;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.RequestProcessingPolicyValue;
import org.omg.PortableServer.ThreadPolicyValue;

public class Server_DefaultServant {

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

			// Get a reference to the root POA
			org.omg.CORBA.Object obj = orb.resolve_initial_references("RootPOA");
			POA poaRoot = POAHelper.narrow(obj);

			// Create policies for our persistent POA
			org.omg.CORBA.Policy[] policies = {
					poaRoot.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
					poaRoot.create_id_assignment_policy(IdAssignmentPolicyValue.USER_ID),
					poaRoot.create_thread_policy(ThreadPolicyValue.ORB_CTRL_MODEL),
					poaRoot.create_request_processing_policy(RequestProcessingPolicyValue.USE_DEFAULT_SERVANT),
					poaRoot.create_id_uniqueness_policy(IdUniquenessPolicyValue.MULTIPLE_ID)
			};

			// Create myPOA with the right policies
			POA poa = poaRoot.create_POA("HelloServiceDefaultServerImpl_poa",	poaRoot.the_POAManager(), policies);

			// Create the servant
			HelloServiceDefaultServerImpl servant = new HelloServiceDefaultServerImpl();
			poa.set_servant(servant);

			byte[] objectId = "AnyObjectID".getBytes();
			String typeId = com.micmiu.corba.openorb.hello.HelloServiceHelper.type().id();
			obj = poa.create_reference_with_id(objectId, typeId);
			
			// Activate the POA manager
			poaRoot.the_POAManager().activate();

			// ---- Uncomment below to enable Naming Service access. ----
			// org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
			// NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			// nc.bind(nc.to_name("MyServerObject"), obj);

			PrintWriter ps = new PrintWriter(new FileOutputStream(new File("server.ior")));
			ps.println(orb.object_to_string(obj));
			ps.close();

			System.out.println("CORBA Server (Default Servant) ready...");

			// Wait for incoming requests
			orb.run();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
