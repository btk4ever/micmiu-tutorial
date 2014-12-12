package com.micmiu.corba.jacorb.hello.client;

import com.micmiu.corba.jacorb.hello.HelloService;
import com.micmiu.corba.jacorb.hello.HelloServiceHelper;
import org.omg.CORBA.ORB;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Properties;

/**
 * JacORB NameService 客户端
 * 测试客户端之前需要 启动监听 和服务端程序
 * 客户端运行 需要 +参数 -ORBInitRef NameService=corbaloc::127.0.0.1:1234/NameService
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/11/2014
 * Time: 10:50
 */
public class HelloJacORBIORClient {

	private ORB orb = null;
	private HelloService helloService = null;

	public HelloJacORBIORClient() throws Exception {
		initORB(null);
	}

	public HelloJacORBIORClient(String[] args) throws Exception {
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

		LineNumberReader input = new LineNumberReader(new FileReader("ior/hello-jacorb-server.ior"));
		String ior = input.readLine();
		input.close();
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
			HelloJacORBIORClient client = new HelloJacORBIORClient(args);
			String ret = client.getORBService().sayHello("micmiu.com");
			System.out.println("[客户端] 调用结果 : " + ret);
			client.shutdown();
			System.out.println("[客户端] 运行结束.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
