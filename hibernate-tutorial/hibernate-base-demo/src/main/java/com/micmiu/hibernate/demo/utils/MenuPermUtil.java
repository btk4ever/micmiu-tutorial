package com.micmiu.hibernate.demo.utils;

import java.util.List;
import java.util.Set;

import com.micmiu.hibernate.demo.entity.Menu;
import com.micmiu.hibernate.demo.vo.MenuVo;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class MenuPermUtil {

	public static void parseUserMenu(List<Menu> allMenus, List<MenuVo> userMenus,
			Set<Long> ids) {
		for (Menu menu : allMenus) {
			if (ids.contains(menu.getId())) {
				userMenus.add(parseMenu(ids, menu));
			}
		}
	}

	private static MenuVo parseMenu(Set<Long> ids, Menu menu) {
		MenuVo vo = new MenuVo();
		vo.setId(menu.getId());
		vo.setMenuName(menu.getMenuName());
		if (!menu.getChildren().isEmpty()) {
			for (Menu childMenu : menu.getChildren()) {
				if (ids.contains(childMenu.getId())) {
					vo.getChildren().add(parseMenu(ids, childMenu));
				}
			}
		}
		return vo;
	}

}
