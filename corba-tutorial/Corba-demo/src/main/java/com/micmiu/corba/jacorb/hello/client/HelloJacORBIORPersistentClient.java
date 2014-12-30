package com.micmiu.corba.jacorb.hello.client;

import com.micmiu.corba.jacorb.hello.HelloService;
import com.micmiu.corba.jacorb.hello.HelloServiceHelper;
import org.omg.CORBA.ORB;

import java.util.Properties;

/**
 * JacORB 固定IOR 客户端调用
 * 测试客户端之前需要 运行服务端程序
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/11/2014
 * Time: 10:50
 */
public class HelloJacORBIORPersistentClient {

	private ORB orb = null;
	private HelloService helloService = null;

	public HelloJacORBIORPersistentClient() throws Exception {
		initORB(null);
	}

	public HelloJacORBIORPersistentClient(String[] args) throws Exception {
		initORB(args);
	}

	/**
	 * Initialize ORB.
	 *
	 * @param args
	 * @throws Exception
	 */
	public void initORB(String[] args) throws Exception {
		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");

		// Initialize the ORB
		orb = ORB.init((String[]) args, props);

		//服务端程序HelloJacORBIORPersistentServer 生成的IOR
		String ior = "IOR:000000000000003349444C3A636F6D2F6D69636D69752F636F7262612F6A61636F72622F68656C6C6F2F48656C6C6F536572766963653A312E300000000000010000000000000094000102000000000E3139322E3136382E312E313030003039000000355374616E64617264496D706C4E616D652F50657273697374656E74504F412F48656C6C6F536572766963654A61634F7262496D706C000000000000020000000000000008000000004A4143000000000100000024000000000501000100000002000100010001000F00010109000000020501000100010100";
		org.omg.CORBA.Object obj = orb.string_to_object(ior);

		helloService = HelloServiceHelper.narrow(obj);
	}

	/**
	 * Obtain ORB Interface.
	 *
	 * @return
	 */
	public HelloService getORBService() {
		return helloService;
	}

	/**
	 * Shutdown ORB.
	 */
	public void shutdown() {
		orb.shutdown(true);
	}

	/**
	 * 测试客户端之前需要 启动监听 和服务端程序
	 * 客户端运行 需要 +参数  -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HelloJacORBIORPersistentClient client = new HelloJacORBIORPersistentClient(args);
			String ret = client.getORBService().sayHello("micmiu.com");
			System.out.println("[客户端] 调用结果 : " + ret);
			client.shutdown();
			System.out.println("[客户端] 运行结束.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
