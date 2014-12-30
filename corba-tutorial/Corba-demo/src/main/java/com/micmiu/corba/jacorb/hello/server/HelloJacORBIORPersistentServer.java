package com.micmiu.corba.jacorb.hello.server;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.*;

import java.util.Properties;

/**
 * 持久化POA 固定IOR 服务端
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 12/30/2014
 * Time: 17:08
 */
public class HelloJacORBIORPersistentServer {

	//持久化的POA
	private static POA persistentPOA = null;

	public static void main(String[] args) {
		//生成一个对象请求代理（ORB），并初始化
		Properties props = new Properties();
		//使用jacorb的CORBA实现方案
		props.put("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		props.put("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		//使用持久化IOR，必须指定持久化POA的实现名称，默认是"StandardImplName"，
		//可以随便指定
		props.put("jacorb.implname", "StandardImplName");
		//这里是指定CORBA服务器端端口为固定端口，是CORBA的IOR固定不变的关键  注意下面两种只能二选其一
		//只指定端口 指定属性 OAPort，
		//如果需要指定服务器端的ip地址和端口，需要配置属性 OAAddress
		props.put("OAPort", "12345");
		//props.put("OAAddress", "iiop://10.140.1.97:12345");
		try {
			//创建ORB实例
			ORB orb = ORB.init(args, props);

			//得到一个 RootPOA引用
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			//指定创建持久化POA的策略，使用持久化POA必须指定以下三种策略
			org.omg.CORBA.Policy[] policies = new org.omg.CORBA.Policy[3];
			//POA生命周期是持久化
			policies[0] = rootPoa.create_lifespan_policy(LifespanPolicyValue.PERSISTENT);
			//CORBA对象的标识符是用户指定的
			policies[1] = rootPoa.create_id_assignment_policy(IdAssignmentPolicyValue.USER_ID);
			//每次接到一个请求时，POA期望应用程序提供目标对象标识符作为查找伺服程序的索引
			policies[2] = rootPoa.create_servant_retention_policy(ServantRetentionPolicyValue.RETAIN);
			//创建持久化的POA
			persistentPOA = rootPoa.create_POA("PersistentPOA", rootPoa.the_POAManager(), policies);
			//清除策略
			policies[0].destroy();
			policies[1].destroy();
			policies[2].destroy();
			policies = null;

			//创建伺服程序并注册到ORB上
			HelloServiceJacOrbImpl helloImpl = new HelloServiceJacOrbImpl();
			//helloImpl.setOrb(orb);

			//创建伺服程序标识符，因为使用IdAssignmentPolicyValue.USER_ID
			//策略，所有必须要指定伺服程序id
			byte[] servantId = "HelloServiceJacOrbImpl".getBytes();
			//将伺服程序标识符和服务器端CORBA对象关联起来并激活
			persistentPOA.activate_object_with_id(servantId, helloImpl);
			//激活POAManager
			rootPoa.the_POAManager().activate();
			//通过持久化POA获取CORBA对象
			org.omg.CORBA.Object ref = persistentPOA.servant_to_reference(helloImpl);
			//打印CORBA对象的IOR
			System.out.println("CORBA [IOR] = " + orb.object_to_string(ref));

			System.out.println("CORBA[JacORB] Persistent Server ready...");

			//启动线程服务，等待调用
			orb.run();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
