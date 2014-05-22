package com.micmiu.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 单元测试的抽象类
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-6-6 下午7:31:02
 * @version 1.0
 */
public abstract class AbstractHibernateBaseTest {

	protected static SessionFactory sessionFactory;

	@BeforeClass
	public static void beforeClass() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}

	@Test
	public abstract void testMethod();

}
