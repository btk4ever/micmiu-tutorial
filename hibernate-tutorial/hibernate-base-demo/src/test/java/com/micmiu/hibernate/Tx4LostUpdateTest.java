package com.micmiu.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.BankAccount;

/**
 * 
 * Description: hibernate 第一类丢失更新
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-2 上午10:47:00
 * @version 1.0
 */
public class Tx4LostUpdateTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		String hql = "select t from BankAccount t where t.userId=:userId";

		// T1
		System.out.println("===========>  T1");
		Transaction tx1 = session1.beginTransaction();

		// T2
		System.out.println("===========>  T2");
		Transaction tx2 = session2.beginTransaction();

		// T3
		System.out.println("===========>  T3");
		BankAccount e1 = (BankAccount) session1.createQuery(hql)
				.setParameter("userId", "michael@micmiu.com").uniqueResult();
		System.out.println(e1);

		// T4
		System.out.println("===========>  T4");
		BankAccount e2 = (BankAccount) session2.createQuery(hql)
				.setParameter("userId", "michael@micmiu.com").uniqueResult();
		System.out.println(e2);

		// T5
		System.out.println("===========>  T5");
		e1.setAmount(70000.00);
		e1.setNote("tx1 e1 T5");
		session1.update(e1);

		// T6
		System.out.println("===========>  T6");
		e2.setAmount(40000.00);
		e2.setNote("tx2 e2 T6");
		session2.update(e2);
		tx2.commit();
		session2.close();

		// T7
		System.out.println("===========>  T7");
		tx1.rollback();
		session1.close();

		System.out.println("===========>  END");
		// sessionFactory.getCache().evictEntityRegion(BankAccount.class);

		Session session = sessionFactory.openSession();
		System.out
				.println(session.get(BankAccount.class, "michael@micmiu.com"));
		session.close();

	}

}
