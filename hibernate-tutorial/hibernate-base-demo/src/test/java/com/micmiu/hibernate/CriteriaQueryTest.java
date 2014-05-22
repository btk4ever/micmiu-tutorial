package com.micmiu.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.Contact;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @version 1.0
 */
public class CriteriaQueryTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Contact.class, "c");
		criteria.createAlias("author", "a");
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("a.username").as("ausername"))
				.add(Projections.property("c.type").as("ctype"))
				.add(Projections.property("c.details").as("cdetails")));
		criteria.add(Restrictions.like("a.username", "%m%"))
				.addOrder(Order.asc("a.username"))
				.addOrder(Order.asc("c.type"));
		// criteria.setResultTransformer(Transformers.aliasToBean(Object[].class));

		@SuppressWarnings("unchecked")
		List<Object[]> list = criteria.list();
		for (Object[] arr : list) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " -- ");
			}
			System.out.println();
		}
		tx.commit();
	}

}
