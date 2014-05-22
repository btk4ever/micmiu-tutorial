package com.micmiu.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.Company;
import com.micmiu.hibernate.demo.entity.Employee;

/**
 * 
 * 测试 注解配置单向一对多的CRUD
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-7 上午11:38:49
 * @version 1.0
 */
public class OneToManyUnidirectionalCRUDTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		int[] ids = testCreate();

		// int[] ids = new int[] { 68, 66, 70 };
		testUpdate(ids);
		testReadDelete(ids);
	}

	/**
	 * 测试添加
	 * 
	 * @return
	 */
	public int[] testCreate() {
		System.out.println(">>>> 测试 添加");
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		int beforeOneCount = session.createCriteria(Company.class).list()
				.size();
		int beforeManyCount = session.createCriteria(Employee.class).list()
				.size();

		Company company = new Company();
		company.setCompanyName("micmiu");
		company.setCompanySite("http://www.micmiu.com");

		List<Employee> employees = new ArrayList<Employee>();
		Employee e1 = new Employee();
		e1.setUsername("michael");
		e1.setMail("michael@micmiu.com");
		e1.setSexType("male");
		employees.add(e1);

		Employee e2 = new Employee();
		e2.setUsername("hazel");
		e2.setMail("hazel@micmiu.com");
		e2.setSexType("female");
		employees.add(e2);

		company.setEmployees(employees);

		// 单向1:N需要手工持久化与之关联的N端数据
		session.save(e1);
		session.save(e2);

		session.save(company);

		int afterOneCount = session.createCriteria(Company.class).list().size();
		int afterManyCount = session.createCriteria(Employee.class).list()
				.size();
		Assert.assertEquals(beforeOneCount + 1, afterOneCount);
		Assert.assertEquals(beforeManyCount + 2, afterManyCount);

		session.getTransaction().commit();
		session.close();
		return new int[] { company.getId(), e1.getId(), e2.getId() };

	}

	/**
	 * 测试更新
	 * 
	 * @param ids
	 */
	public void testUpdate(int[] ids) {
		System.out.println(">>>> 测试 更新");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		int oneId = ids[0];
		int many1Id = ids[1];
		int many2Id = ids[2];

		Employee e1 = (Employee) session.get(Employee.class, many1Id);

		System.out.println(">>>> 更新 1:N中 N的数据");
		System.out.println(e1);
		e1.setMail("update@micmiu.com");
		session.update(e1);
		System.out.println(">>>> 更新后");
		Employee e2 = (Employee) session.get(Employee.class, many1Id);
		System.out.println(e2);
		Assert.assertEquals("update@micmiu.com", e2.getMail());

		System.out.println(">>>> 更新 1:N中 1的数据");
		Company company = (Company) session.get(Company.class, oneId);
		System.out.println(company);
		company.setCompanyName("大大和小小");

		List<Employee> employees = company.getEmployees();
		List<Employee> removes = new ArrayList<Employee>();
		for (Employee e : employees) {
			if (many2Id == e.getId().intValue()) {
				removes.add(e);
				session.delete(e);
				continue;
			}
		}
		employees.removeAll(removes);
		Employee e3 = new Employee();
		e3.setUsername("test");
		e3.setMail("test@micmiu.com");
		e3.setSexType("male");
		employees.add(e3);
		session.save(e3);
		company.setEmployees(employees);
		session.update(company);

		Company company2 = (Company) session.get(Company.class, oneId);
		for (Employee e : company2.getEmployees()) {
			System.out.println(e);
			Assert.assertNotEquals(many2Id, e.getId().intValue());
		}
		Assert.assertEquals(2, company2.getEmployees().size());
		Assert.assertEquals("大大和小小", company2.getCompanyName());

		tx.commit();
		session.close();
	}

	public void testReadDelete(int[] ids) {
		System.out.println(">>>> 测试 读取和删除");
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		System.out.println(">>>> 查询初始");
		int beforeOneCount = session.createCriteria(Company.class).list()
				.size();
		int beforeManyCount = session.createCriteria(Employee.class).list()
				.size();
		System.out.println(">>>> size  one = " + beforeOneCount + " many ="
				+ beforeManyCount);

		int oneId = ids[0];
		int manyId = ids[1];

		System.out.println(">>>> 查询 1:N中 N的数据");
		Employee e1 = (Employee) session.get(Employee.class, manyId);
		System.out.println(e1);
		Assert.assertEquals(manyId, e1.getId().intValue());

		System.out.println(">>>> 删除1:N中 N的数据后，查询N的数据");
		session.delete(e1);

		int actualManyCount2 = session.createCriteria(Employee.class).list()
				.size();
		System.out.println("count many = " + actualManyCount2);
		Assert.assertEquals(beforeManyCount - 1, actualManyCount2);

		System.out.println(">>>> 查询 1:N中 1的数据");
		Company company = (Company) session.get(Company.class, oneId);
		System.out.println(company);
		Assert.assertEquals(oneId, company.getId().intValue());

		System.out.println(">>>> 删除1:N中 1的数据后，查询1的数据");
		session.delete(company);
		int actualOntCount = session.createCriteria(Company.class).list()
				.size();
		System.out.println(">>>> size one = " + actualOntCount);
		Assert.assertEquals(beforeOneCount - 1, actualOntCount);

		System.out.println(">>>> 删除1:N中 1的数据后，查询N的数据");
		int actualManyCount = session.createCriteria(Employee.class).list()
				.size();
		System.out.println(">>>> size many = " + actualManyCount);
		Assert.assertEquals(beforeManyCount - 2, actualManyCount);

		session.getTransaction().commit();
		session.close();
	}

}
