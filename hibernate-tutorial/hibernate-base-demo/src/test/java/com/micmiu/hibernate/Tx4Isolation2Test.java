package com.micmiu.hibernate;

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
public class Tx4Isolation2Test extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		Session session2 = sessionFactory.openSession();

		System.out.println("===========>  beginTransaction");
		Transaction tx2 = session2.beginTransaction();

		System.out.println("===========>  insert new record");
		BankAccount newE = new BankAccount();
		newE.setUserId("admin@micmiu.com");
		newE.setAmount(9999.99d);
		newE.setUsername("admin");
		newE.setNote("add new");
		newE.setVersion(1);
		session2.save(newE);
		
		String userid = "michael@micmiu.com";
		BankAccount e2 = (BankAccount) session2.get(BankAccount.class, userid);
		System.out.println(e2);
		e2.setAmount(50002.00d);
		e2.setNote("tx2 update");
		session2.update(e2);

		System.out.println("===========>  tx commit");
		tx2.commit();
		session2.close();

		System.out.println("===========>  END");

	}

}
