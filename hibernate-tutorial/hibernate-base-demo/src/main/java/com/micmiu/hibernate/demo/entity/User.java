package com.micmiu.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * 用户.
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Entity
@Table(name = "DEMO_T_USER")
public class User extends IdEntity {

	@Column(name = "LOGIN_NAME")
	private String loginName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "OTHER")
	private String other;

	private Collection<Role> roleList = new ArrayList<Role>();

	@ManyToMany
	@JoinTable(name = "DEMO_R_U2R", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	public Collection<Role> getRoleList() {
		return roleList;
	}

	@Transient
	public Set<Long> getMenuIds() {
		Set<Long> ids = new HashSet<Long>();
		for (Role role : getRoleList()) {
			for (Permssion perm : role.getPermssions()) {
				parseMenuIds(ids, perm.getMenu());
			}
		}
		return ids;

	}

	private void parseMenuIds(Set<Long> menuIds, Menu menu) {
		menuIds.add(menu.getId());
		if (null != menu.getParent()) {
			parseMenuIds(menuIds, menu.getParent());
		}
	}

	public void setRoleList(Collection<Role> roleList) {
		this.roleList = roleList;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getOther() {
		return other;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}