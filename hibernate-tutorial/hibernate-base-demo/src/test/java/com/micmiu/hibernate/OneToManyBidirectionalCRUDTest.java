package com.micmiu.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import com.micmiu.hibernate.demo.entity.Author;
import com.micmiu.hibernate.demo.entity.Contact;

/**
 * 
 * 测试 注解配置双向一对多的CRUD
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-7 上午11:38:49
 * @version 1.0
 */
public class OneToManyBidirectionalCRUDTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		long[] ids = testCreate();
		testUpdate(ids);
		testReadDelete(ids);
	}

	public long[] testCreate() {
		System.out.println(">>>> 测试 添加");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		int beforeCountAuthor = session.createCriteria(Author.class).list()
				.size();
		int beforeCountContact = session.createCriteria(Contact.class).list()
				.size();

		Author author = new Author();
		author.setUsername("test");
		author.setTitle("测试人员");
		author.setSexType("male");
		author.setDescription("测试");

		List<Contact> contacts = new ArrayList<Contact>();
		Contact c1 = new Contact();
		c1.setAuthor(author);
		c1.setType("blog");
		c1.setDetails("http://www.micmiu.com/author/test");
		c1.setOther("");
		contacts.add(c1);

		Contact c2 = new Contact();
		c2.setAuthor(author);
		c2.setType("mail");
		c2.setDetails("test@micmiu.com");
		c2.setOther("");
		contacts.add(c2);

		// 双向1:N N:1 自动持久化关联的数据
		author.setContacts(contacts);
		session.save(author);

		System.out.println(">>> one id = " + author.getId());
		System.out.println(">>> many id = " + c1.getId());
		System.out.println(">>> many id = " + c2.getId());

		int expectedCountAuthor = session.createCriteria(Author.class).list()
				.size();
		int expectedCountContact = session.createCriteria(Contact.class).list()
				.size();

		Assert.assertEquals(expectedCountAuthor, beforeCountAuthor + 1);
		Assert.assertEquals(expectedCountContact, beforeCountContact + 2);
		tx.commit();
		session.close();
		return new long[] { author.getId(), c1.getId(), c2.getId() };

	}

	public void testUpdate(long[] ids) {
		System.out.println(">>>> 测试 更新");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		long authorId = ids[0];
		long contactId = ids[1];
		long contact2Id = ids[2];

		System.out.println(">>>> 更新 1:N中 N的数据");
		Contact contact = (Contact) session.get(Contact.class, contactId);
		System.out.println(contact);
		contact.setOther("update");
		session.update(contact);

		Contact contact2 = (Contact) session.get(Contact.class, contactId);
		System.out.println(contact2);
		Assert.assertEquals("update", contact2.getOther());

		System.out.println(">>>> 更新 1:N中 1的数据");
		Author author = (Author) session.get(Author.class, authorId);
		System.out.println(author);
		author.setDescription("update");

		List<Contact> contacts = author.getContacts();
		List<Contact> removes = new ArrayList<Contact>();
		for (Contact c : contacts) {
			if (c.getId().longValue() == contact2Id) {
				removes.add(c);
				session.delete(c);
				continue;
			}
		}
		contacts.removeAll(removes);

		Contact cn = new Contact();
		cn.setAuthor(author);
		cn.setType("blog");
		cn.setDetails("http://www.micmiu.com/author/testnew");
		cn.setOther("addnew");
		contacts.add(cn);

		author.setContacts(contacts);
		session.update(author);

		Author author2 = (Author) session.get(Author.class, authorId);
		for (Contact c : author2.getContacts()) {
			System.out.println(c);
		}
		Assert.assertEquals("update", author2.getDescription());

		tx.commit();
		session.close();
	}

	public void testReadDelete(long[] ids) {

		System.out.println(">>>> 测试读取和删除");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println(">>>> 查询初始");
		int beforeCountAuthor = session.createCriteria(Author.class).list()
				.size();
		int beforeCountContact = session.createCriteria(Contact.class).list()
				.size();
		System.out.println(">>>> size  one = " + beforeCountAuthor + " many ="
				+ beforeCountContact);

		long authorId = ids[0];
		long contactId = ids[1];

		System.out.println(">>>> 查询 1:N中 N的数据");
		Contact contact = (Contact) session.get(Contact.class, contactId);
		System.out.println(contact);
		Assert.assertEquals(contactId, contact.getId().longValue());

		System.out.println(">>>> 删除1:N中 N的数据后，查询N的数据");
		session.delete(contact);

		int actualContactCount2 = session.createCriteria(Contact.class).list()
				.size();
		System.out.println(">>>> size many" + actualContactCount2);
		Assert.assertEquals(beforeCountContact - 1, actualContactCount2);

		System.out.println(">>>> 查询 1:N中 1的数据");
		Author author = (Author) session.get(Author.class, authorId);
		System.out.println(author);
		Assert.assertEquals(authorId, author.getId().longValue());

		System.out.println(">>>> 删除1:N中 1的数据后，查询1的数据");
		session.delete(author);
		int actualCountAuthor = session.createCriteria(Author.class).list()
				.size();
		System.out.println(">>>> size one = " + actualCountAuthor);
		Assert.assertEquals(beforeCountAuthor - 1, actualCountAuthor);

		System.out.println(">>>> 删除1:N中 1的数据后，查询N的数据");
		int actualCountContact = session.createCriteria(Contact.class).list()
				.size();
		Assert.assertEquals(actualCountContact, beforeCountContact - 2);
		System.out.println(">>>> size many = " + actualCountContact);

		tx.commit();
		session.close();
	}

}
