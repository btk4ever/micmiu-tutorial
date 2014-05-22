package com.micmiu.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.BankAccount;

/**
 * Description:第二类丢失更新
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-5 下午4:15:23
 * @version 1.0
 */
public class Tx4SecondLostUpdateTest extends AbstractHibernateBaseTest {
	@Override
	@Test
	public void testMethod() {
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		String userid = "michael@micmiu.com";

		// T1
		System.out.println("===========>  T1");
		Transaction tx1 = session1.beginTransaction();

		// T2
		System.out.println("===========>  T2");
		Transaction tx2 = session2.beginTransaction();

		// T3
		System.out.println("===========>  T3");
		BankAccount e1 = (BankAccount) session1.get(BankAccount.class, userid);
		System.out.println(e1);
		e1.setAmount(50001.00d);
		session1.update(e1);

		// T4
		System.out.println("===========>  T4");

		// T5
		System.out.println("===========>  T5");
		BankAccount e2 = (BankAccount) session2.get(BankAccount.class, userid);
		System.out.println(e2);
		e2.setAmount(50002.00d);
		session2.update(e2);

		// T6
		System.out.println("===========>  T6");
		tx1.commit();
		session1.close();

		// T7
		System.out.println("===========>  T7");
		tx2.commit();
		session2.close();
		System.out.println("===========>  END");

	}
}