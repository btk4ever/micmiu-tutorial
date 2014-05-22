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
 * 测试 注解配置单向多对多关系的CRUD
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-7 上午11:38:49
 * @version 1.0
 */
public class ManyToManyUnidirectionalCRUDTest extends AbstractHibernateBaseTest {

	@Override
	@Test
	public void testMethod() {
		delete();
	}

	/**
	 * 测试 delete
	 */
	public void delete() {

	}
}
