package com.micmiu.hibernate.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */

@Entity
@Table(name = "DEMO_T_MENU")
public class Menu extends IdEntity {

	private String menuName;

	private String aliasName;

	private String menuURL;

	private String menuType;

	private Integer orderNum;

	private Set<Menu> children = new HashSet<Menu>();

	private Menu parent;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PARENT_ID")
	public Menu getParent() {
		return parent;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	public Set<Menu> getChildren() {
		return children;
	}

	@Column(name = "MENU_NAME", length = 50)
	public String getMenuName() {
		return menuName;
	}

	@Column(name = "ALIAS_NAME", length = 50)
	public String getAliasName() {
		return aliasName;
	}

	@Column(name = "MENU_URL", length = 512)
	public String getMenuURL() {
		return menuURL;
	}

	@Column(name = "MENU_TYPE", length = 10)
	public String getMenuType() {
		return menuType;
	}

	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
