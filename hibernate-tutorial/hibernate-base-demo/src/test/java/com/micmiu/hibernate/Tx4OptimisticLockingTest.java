package com.micmiu.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.BankAccount;

/**
 * 
 * Description: hibernate 乐观锁 并发测试
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-2 上午10:47:00
 * @version 1.0
 */
public class Tx4OptimisticLockingTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		String hql = "select t from BankAccount t where t.userId=:userId";
		BankAccount e1 = (BankAccount) session1.createQuery(hql)
				.setParameter("userId", "michael@micmiu.com").uniqueResult();
		BankAccount e2 = (BankAccount) session2.createQuery(hql)
				.setParameter("userId", "michael@micmiu.com").uniqueResult();
		System.out.println("version:: e1= " + e1.getVersion() + " - e2= "
				+ e2.getVersion());
		Assert.assertEquals(e1.getVersion(), e2.getVersion());

		Transaction tx1 = session1.beginTransaction();
		e1.setNote("update by 1");
		tx1.commit();
		System.out.println("version:: e1= " + e1.getVersion() + " - e2= "
				+ e2.getVersion());
		Assert.assertNotEquals(e1.getVersion(), e2.getVersion());

		try {
			Thread.sleep(1000L * 3);
		} catch (Exception e) {
		}

		Transaction tx2 = session2.beginTransaction();
		e2.setNote("update by 2");
		tx2.commit();
	}

}
