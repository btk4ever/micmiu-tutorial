package com.micmiu.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.BankAccount;

/**
 * 
 * Description:脏读
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-5 下午3:16:55
 * @version 1.0
 */
public class Tx4DirtyReadTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		// String hql = "select t from BankAccount t where t.userId=:userId";
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
		double amount = e1.getAmount();

		// T4
		System.out.println("===========>  T4");

		// T5
		System.out.println("===========>  T5");
		e1.setAmount(amount + 5000);
		e1.setNote("tx1 T5 " + amount + " + 5000");
		session1.update(e1);

		// T6
		System.out.println("===========>  T6");
		BankAccount e2 = (BankAccount) session2.get(BankAccount.class, userid);
		System.out.println(e2);
		Assert.assertEquals(amount, e2.getAmount().doubleValue(), 0);
		tx2.commit();
		session2.close();

		// T7
		System.out.println("===========>  T7");
		tx1.rollback();
		session1.close();

		System.out.println("===========>  END");

	}

}
