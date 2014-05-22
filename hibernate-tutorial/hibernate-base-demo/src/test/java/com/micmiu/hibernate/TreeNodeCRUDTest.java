package com.micmiu.hibernate;

import org.hibernate.Session;

import com.micmiu.hibernate.demo.entity.TreeNode;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class TreeNodeCRUDTest extends AbstractHibernateBaseTest {

	public void testMethod() {
		testSave();
		testRead();
	}

	public void testSave() {
		Session session = sessionFactory.openSession();

		// 一级
		TreeNode node0 = new TreeNode("Michael");

		// 一级
		TreeNode node0_0 = new TreeNode("J2EE");
		node0_0.setParent(node0);
		node0.getChildren().add(node0_0);

		TreeNode node0_1 = new TreeNode("SOA");
		node0_1.setParent(node0);
		node0.getChildren().add(node0_1);

		TreeNode node0_2 = new TreeNode("NoSQL");
		node0_2.setParent(node0);
		node0.getChildren().add(node0_2);

		TreeNode node0_3 = new TreeNode("编程语言");

		// 三级
		TreeNode node0_3_0 = new TreeNode("Java");
		node0_3_0.setParent(node0_3);
		node0_3.getChildren().add(node0_3_0);

		TreeNode node0_3_1 = new TreeNode("Groovy");
		node0_3_1.setParent(node0_3);
		node0_3.getChildren().add(node0_3_1);

		TreeNode node0_3_2 = new TreeNode("javascript");
		node0_3_2.setParent(node0_3);
		node0_3.getChildren().add(node0_3_2);

		node0_3.setParent(node0);
		node0.getChildren().add(node0_3);

		TreeNode node1 = new TreeNode("Hazel");

		TreeNode node1_0 = new TreeNode("life");
		node1_0.setParent(node1);
		node1.getChildren().add(node1_0);

		TreeNode node1_1 = new TreeNode("美食");
		node1_1.setParent(node1);
		node1.getChildren().add(node1_1);

		TreeNode node1_2 = new TreeNode("旅游");
		node1_2.setParent(node1);
		node1.getChildren().add(node1_2);

		session.beginTransaction();
		session.save(node0);
		session.save(node1);
		session.getTransaction().commit();
		session.close();
	}

	public void testRead() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		TreeNode node = (TreeNode) session.load(TreeNode.class, 1);
		printNode(node, 0);
		session.getTransaction().commit();
		session.close();
	}

	private void printNode(TreeNode node, int level) {
		String preStr = "";
		for (int i = 0; i < level; i++) {
			preStr += "----";
		}
		System.out.println(preStr + node.getName());
		for (TreeNode children : node.getChildren()) {
			printNode(children, level + 1);
		}
	}
}
