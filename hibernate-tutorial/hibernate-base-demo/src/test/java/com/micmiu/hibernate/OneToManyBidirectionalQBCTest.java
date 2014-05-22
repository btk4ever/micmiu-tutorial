package com.micmiu.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.Author;
import com.micmiu.hibernate.demo.entity.Contact;

/**
 * 
 * 测试QBC关联查询 注解配置双向一对多关系的实体
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-7 上午11:38:49
 * @version 1.0
 */
public class OneToManyBidirectionalQBCTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		testQBC4One();
		testQBC4Many();
	}

	/**
	 * 测试Criteria查询1:N的1端数据
	 */
	@SuppressWarnings("unchecked")
	public void testQBC4One() {
		System.out.println(">>>> 测试查询 one 端数据");
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Author.class, "a");
		criteria.createAlias("a.contacts", "c");
		criteria.add(Restrictions.eq("a.sexType", "male")).add(
				Restrictions.eq("c.type", "mail"));
		criteria.addOrder(Order.asc("a.username"));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Author> list = criteria.list();
		System.out.println(">>>> one-to-many 中 one size:" + list.size());
		for (Author c : list) {
			System.out.println(c);
		}
		Assert.assertEquals(1, list.size());
		session.close();
	}

	/**
	 * 测试Criteria查询1:N的N端数据
	 */
	@SuppressWarnings("unchecked")
	public void testQBC4Many() {
		System.out.println(">>>> 测试查询 many 端数据");
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Contact.class, "c");
		criteria.add(Restrictions.eq("c.type", "blog"))
				.createAlias("author", "a")
				.add(Restrictions.eq("a.username", "michael"))
				.add(Restrictions.eq("a.title", "攻城师"))
				.add(Restrictions.like("c.details", "%micmiu.com%"));

		List<Contact> list = criteria.list();
		System.out.println(">>>> one-to-many 中 many size:" + list.size());
		for (Contact c : list) {
			System.out.println(c);
		}
		Assert.assertEquals(1, list.size());
		session.close();
	}
}
