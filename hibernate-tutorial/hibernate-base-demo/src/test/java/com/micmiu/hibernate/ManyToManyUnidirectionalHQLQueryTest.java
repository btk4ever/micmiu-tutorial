package com.micmiu.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.Course;
import com.micmiu.hibernate.demo.entity.Student;

/**
 * 
 * Description:测试 注解配置多对多关系的表之间的联合查询
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-7 上午11:38:49
 * @version 1.0
 */
public class ManyToManyUnidirectionalHQLQueryTest extends
		AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		// 测试查询有配置多对多关系的对象
		test1();
		// 测试查询无配置多对多关系的对象
		test2();

	}

	/**
	 * 测试查询有配置多对多关系的对象
	 */
	@SuppressWarnings("unchecked")
	public void test1() {
		System.out.println(">>>> 测试查询有配置多对多关系的对象");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println(">>>> hql by join ");
		String hql = "select s from Student s join s.courses c where s.name like '%micmiu.com%' and c.name ='math'";
		Query query = session.createQuery(hql);
		List<Student> list = query.list();
		System.out.println(">>>> query size:" + list.size());
		Assert.assertEquals(1, list.size());
		for (Student s : list) {
			Assert.assertEquals("micmiu.com", s.getName());
			System.out.println(s);
		}

		System.out.println(">>>> hql by in elements()");
		hql = "select s from Student s,Course c where c.id in elements (s.courses) and s.name like '%micmiu.com%' and c.name ='math'";
		query = session.createQuery(hql);
		list = query.list();
		System.out.println(">>>> query size:" + list.size());
		Assert.assertEquals(1, list.size());
		for (Student s : list) {
			Assert.assertEquals("micmiu.com", s.getName());
			System.out.println(s);
		}

		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 测试查询无配置多对多关系的对象
	 */
	@SuppressWarnings("unchecked")
	public void test2() {
		System.out.println(">>>> 测试查询无配置多对多关系的对象");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select distinct c from Student s,Course c where c.id in elements (s.courses) and s.name like '%micmiu.com%' and c.name ='math'";

		Query query = session.createQuery(hql);

		List<Course> list = query.list();
		System.out.println(">>>> query size:" + list.size());
		Assert.assertEquals(1, list.size());
		for (Course c : list) {
			Assert.assertEquals("math", c.getName());
			System.out.println(c);
		}

		session.getTransaction().commit();
		session.close();
	}
}
