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
public class Tx4IsolationTest extends AbstractHibernateBaseTest {

	@SuppressWarnings("unchecked")
	@Override
	@Test
	public void testMethod() {
		Session session1 = sessionFactory.openSession();

		String hql = "select t from BankAccount t where t.userId like '%micmiu.com%'";

		System.out.println("===========>  beginTransaction");
		Transaction tx1 = session1.beginTransaction();

		System.out.println("===========>  first  query ... ");
		List<BankAccount> list = session1.createQuery(hql).list();
		System.out.println(list.size());
		for (BankAccount e : list) {
			System.out.println(e);
		}

		try {
			Thread.sleep(20 * 1000L);
		} catch (Exception e) {

		}

		System.out.println("===========>  second query ...");
		List<BankAccount> list2 = session1.createQuery(hql).list();
		System.out.println(list2.size());
		for (BankAccount e : list2) {
			System.out.println(e);
		}
		
		String userid = "michael@micmiu.com";
		BankAccount e1 = (BankAccount) session1.get(BankAccount.class, userid);
		System.out.println(e1);
		e1.setAmount(50001.00d);
		e1.setNote("tx1 update");
		session1.update(e1);
		
		
		System.out.println("===========>  session close");
		tx1.commit();
		session1.close();

		System.out.println("===========>  END");

	}

}
