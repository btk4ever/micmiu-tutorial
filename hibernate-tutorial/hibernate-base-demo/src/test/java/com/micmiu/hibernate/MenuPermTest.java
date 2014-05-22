package com.micmiu.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.micmiu.hibernate.demo.entity.Menu;
import com.micmiu.hibernate.demo.entity.Permssion;
import com.micmiu.hibernate.demo.entity.Role;
import com.micmiu.hibernate.demo.entity.User;
import com.micmiu.hibernate.demo.utils.MenuPermUtil;
import com.micmiu.hibernate.demo.vo.MenuVo;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class MenuPermTest extends AbstractHibernateBaseTest {

	public void testMethod() {
		// testSave();
		// testRead();
		testUserMenu();
	}

	public void testSave() {
		Session session = sessionFactory.openSession();
		Menu menu0 = new Menu();
		menu0.setAliasName("system");
		menu0.setMenuName("系统管理");
		menu0.setMenuURL("");

		Menu menu0_0 = new Menu();
		menu0_0.setAliasName("user");
		menu0_0.setMenuName("用户管理");
		menu0_0.setMenuURL("system/user.do");
		menu0_0.setParent(menu0);
		menu0.getChildren().add(menu0_0);

		Menu menu0_1 = new Menu();
		menu0_1.setAliasName("role");
		menu0_1.setMenuName("角色管理");
		menu0_1.setMenuURL("system/role.do");
		menu0_1.setParent(menu0);
		menu0.getChildren().add(menu0_1);

		Menu menu1 = new Menu();
		menu1.setAliasName("worklog");
		menu1.setMenuName("工作日志");
		menu1.setMenuURL("");

		Menu menu1_0 = new Menu();
		menu1_0.setParent(menu1);
		menu1_0.setAliasName("logperson");
		menu1_0.setMenuName("个人日志");
		menu1_0.setMenuURL("worklog/persion.do");
		menu1.getChildren().add(menu1_0);

		Menu menu1_1 = new Menu();
		menu1_1.setParent(menu1);
		menu1_1.setAliasName("logquery");
		menu1_1.setMenuName("日志查询");
		menu1_1.setMenuURL("worklog/query.do");
		menu1.getChildren().add(menu1_1);

		Menu menu2 = new Menu();
		menu2.setAliasName("notice");
		menu2.setMenuName("公告");
		menu2.setMenuURL("notice.do");

		Role role = new Role();
		role.setRoleName("系统管理员");

		Permssion user_view = new Permssion();
		user_view.setResCnName("用户");
		user_view.setResName("user");
		user_view.setOperation("view");
		user_view.setMenu(menu0_0);
		user_view.setRole(role);
		Permssion user_edit = new Permssion();
		user_edit.setResCnName("用户");
		user_edit.setResName("user");
		user_edit.setOperation("edit");
		user_edit.setMenu(menu0_0);
		user_edit.setRole(role);

		Permssion role_view = new Permssion();
		role_view.setResCnName("角色");
		role_view.setResName("role");
		role_view.setOperation("view");
		role_view.setMenu(menu0_1);
		role_view.setRole(role);
		Permssion role_edit = new Permssion();
		role_edit.setResCnName("角色");
		role_edit.setResName("user");
		role_edit.setOperation("edit");
		role_edit.setMenu(menu0_1);
		role_edit.setRole(role);

		Permssion logp_view = new Permssion();
		logp_view.setResCnName("个人日志");
		logp_view.setResName("logperson");
		logp_view.setOperation("view");
		logp_view.setMenu(menu1_0);
		logp_view.setRole(role);

		Permssion logp_edit = new Permssion();
		logp_edit.setResCnName("个人日志");
		logp_edit.setResName("logperson");
		logp_edit.setOperation("edit");
		logp_edit.setMenu(menu1_0);
		logp_edit.setRole(role);

		Permssion logq_view = new Permssion();
		logq_view.setResCnName("日志查询");
		logq_view.setResName("logquery");
		logq_view.setOperation("view");
		logq_view.setMenu(menu1_1);
		logq_view.setRole(role);

		role.getPermssions().add(logp_view);
		role.getPermssions().add(logp_edit);
		role.getPermssions().add(logq_view);

		role.getPermssions().add(user_view);
		role.getPermssions().add(user_edit);
		role.getPermssions().add(role_view);
		role.getPermssions().add(role_edit);

		Role role1 = new Role();
		role1.setRoleName("普通用户");

		Permssion user_view1 = new Permssion();
		user_view1.setResCnName("用户");
		user_view1.setResName("user");
		user_view1.setOperation("view");
		user_view1.setMenu(menu0_0);
		user_view1.setRole(role1);

		Permssion role_view1 = new Permssion();
		role_view1.setResCnName("角色");
		role_view1.setResName("role");
		role_view1.setOperation("view");
		role_view1.setMenu(menu0_1);
		role_view1.setRole(role1);

		role1.getPermssions().add(user_view1);
		role1.getPermssions().add(role_view1);

		Permssion logp_view1 = new Permssion();
		logp_view1.setResCnName("个人日志");
		logp_view1.setResName("logperson");
		logp_view1.setOperation("view");
		logp_view1.setMenu(menu1_0);
		logp_view1.setRole(role1);

		role1.getPermssions().add(logp_view1);

		User user = new User();
		user.setLoginName("michael");
		user.setName("管理员");
		user.getRoleList().add(role);

		User user1 = new User();
		user1.setLoginName("user");
		user1.setName("普通用户");
		user1.getRoleList().add(role1);

		session.beginTransaction();
		session.save(user);
		session.save(role);

		session.save(user1);
		session.save(role1);
		session.getTransaction().commit();
		session.close();
	}

	public void testRead() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, 2L);
		System.out.println(">>>:" + user);

		for (Long id : user.getMenuIds()) {
			System.out.println("menu id:" + id);
		}

		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public void testUserMenu() {
		Session session = sessionFactory.openSession();
		User user = (User) session.load(User.class, 2L);
		Criterion top = Restrictions.isNull("parent");
		List<Menu> allTopMenus = session.createCriteria(Menu.class).add(top)
				.addOrder(Order.asc("orderNum")).list();
		List<MenuVo> userMenus = new ArrayList<MenuVo>();

		MenuPermUtil.parseUserMenu(allTopMenus, userMenus, user.getMenuIds());
		for (MenuVo vo : userMenus) {
			printMenu(vo, 0);
		}
		session.close();
	}

	private void printMenu(MenuVo menu, int level) {
		String preStr = "";
		for (int i = 0; i < level; i++) {
			preStr += "----";
		}
		System.out.println(preStr + menu.getMenuName());
		for (MenuVo children : menu.getChildren()) {
			printMenu(children, level + 1);
		}
	}

}
