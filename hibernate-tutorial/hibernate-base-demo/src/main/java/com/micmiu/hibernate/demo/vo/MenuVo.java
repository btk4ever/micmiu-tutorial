package com.micmiu.hibernate.demo.vo;

import java.util.HashSet;
import java.util.Set;

public class MenuVo {

	private Long id;

	private String menuName;

	private String aliasName;

	private String menuURL;

	private String menuType;

	private String orderNum;

	private Set<MenuVo> children = new HashSet<MenuVo>();

	private MenuVo parent;

	public Long getId() {
		return id;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public String getMenuType() {
		return menuType;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public Set<MenuVo> getChildren() {
		return children;
	}

	public MenuVo getParent() {
		return parent;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public void setChildren(Set<MenuVo> children) {
		this.children = children;
	}

	public void setParent(MenuVo parent) {
		this.parent = parent;
	}

}
