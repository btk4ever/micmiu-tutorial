package com.micmiu.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Entity
@Table(name = "DEMO_T_PERMSSION")
public class Permssion extends IdEntity {

	private String resName;

	private String resCnName;

	private String operation;

	private Menu menu;

	private Role role;

	@Column(name = "RES_NAME", length = 50)
	public String getResName() {
		return resName;
	}

	@Column(name = "OPERATION", length = 50)
	public String getOperation() {
		return operation;
	}

	@Column(name = "RES_CN_NAME", length = 50)
	public String getResCnName() {
		return resCnName;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MENU_ID")
	public Menu getMenu() {
		return menu;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setResCnName(String resCnName) {
		this.resCnName = resCnName;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
