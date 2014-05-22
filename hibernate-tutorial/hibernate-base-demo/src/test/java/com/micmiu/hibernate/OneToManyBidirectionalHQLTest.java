package com.micmiu.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.Author;
import com.micmiu.hibernate.demo.entity.Contact;

/**
 * 
 * 测试HQL关联查询 注解配置双向一对多关系的实体
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-7 上午11:38:49
 * @version 1.0
 */
public class OneToManyBidirectionalHQLTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {

		testHQL4One();

		testHQL4Many();
	}

	/**
	 * 测试HQL 查询1:N的1端数据
	 */
	@SuppressWarnings("unchecked")
	public void testHQL4One() {
		System.out.println(">>>> 测试查询 one 端数据");
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		String hql = "select a from Author a join a.contacts c where a.username='michael' "
				+ "and a.title='攻城师' and c.type ='blog' and c.details like '%micmiu.com%' ";
		Query query = session.createQuery(hql);

		List<Author> list = query.list();
		System.out.println(">>>> one-to-many 中 one size:" + list.size());
		for (Author a : list) {
			System.out.println(a);
			Assert.assertEquals("michael", a.getUsername());

		}
		Assert.assertEquals(1, list.size());

		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 测试HQL 查询1:N的N端数据
	 */
	@SuppressWarnings("unchecked")
	public void testHQL4Many() {
		System.out.println(">>>> 测试查询 many 端数据");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select c from Contact c join c.author a where a.username='michael' "
				+ "and a.title='攻城师' and c.type ='blog' and c.details like '%micmiu.com%' ";
		Query query = session.createQuery(hql);

		List<Contact> list = query.list();
		System.out.println(">>>> one-to-many 中 many size:" + list.size());
		for (Contact c : list) {
			System.out.println(c);
			Assert.assertTrue(c.getDetails().indexOf("micmiu.com") > 0);

		}
		Assert.assertEquals(1, list.size());

		session.getTransaction().commit();
		session.close();
	}
}
