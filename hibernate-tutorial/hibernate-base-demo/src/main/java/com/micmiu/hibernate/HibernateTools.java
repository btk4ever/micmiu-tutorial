package com.micmiu.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 
 * Description:
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-6 下午5:11:45
 * @version 1.0
 */
public class HibernateTools {

	/**
	 * 
	 */
	public static void initTable() {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			sessionFactory.getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != sessionFactory) {
				sessionFactory.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initTable();

	}

}
