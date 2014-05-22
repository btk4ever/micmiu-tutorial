package com.micmiu.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.BankAccount;

/**
 * Description: 虚读
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-5 下午3:56:52
 * @version 1.0
 */
public class Tx4PhantomReadTest extends AbstractHibernateBaseTest {

	@SuppressWarnings("unchecked")
	@Override
	@Test
	public void testMethod() {
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		String hql = "select t from BankAccount t where t.userId like '%micmiu.com%'";

		// T1
		System.out.println("===========>  T1");
		Transaction tx1 = session1.beginTransaction();

		// T2
		System.out.println("===========>  T2");
		Transaction tx2 = session2.beginTransaction();

		// T3
		System.out.println("===========>  T3");
		List<BankAccount> list = session1.createQuery(hql).list();
		System.out.println(list.size());
		for (BankAccount e : list) {
			System.out.println(e);
		}

		// T4
		System.out.println("===========>  T4");
		BankAccount newE = new BankAccount();
		newE.setUserId("admin@micmiu.com");
		newE.setAmount(9999.99d);
		newE.setUsername("admin");
		newE.setNote("add new");
		newE.setVersion(1);
		session2.save(newE);

		// T5
		System.out.println("===========>  T5");

		// T6
		System.out.println("===========>  T6");
		tx2.commit();
		session2.close();

		// T7
		System.out.println("===========>  T7");
		List<BankAccount> list2 = session1.createQuery(hql).list();
		System.out.println(list2.size());
		for (BankAccount e : list2) {
			System.out.println(e);
		}
		tx1.commit();
		session1.close();

		System.out.println("===========>  END");

	}

}
