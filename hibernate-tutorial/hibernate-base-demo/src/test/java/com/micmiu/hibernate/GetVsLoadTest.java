package com.micmiu.hibernate;

import org.hibernate.Session;

import com.micmiu.hibernate.demo.entity.Author;
import com.micmiu.hibernate.demo.entity.Tree;

public class GetVsLoadTest extends AbstractHibernateBaseTest {

	public void testMethod() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		
		Object  obj = session.load(Author.class, 1L);
		
		System.out.println(obj);
		
		session.getTransaction().commit();
		session.close();
	}
}
