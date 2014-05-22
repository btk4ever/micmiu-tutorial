package com.micmiu.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-19 下午3:14:35
 * @version 1.0
 */
@Entity(name = "DEMO_T_EMPLOYEE")
public class Employee {

	private Integer id;

	private String username;

	private String mail;

	private String sexType;

	private Integer companyId;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	@Column(name = "MAIL")
	public String getMail() {
		return mail;
	}

	@Column(name = "SEX_TYPE")
	public String getSexType() {
		return sexType;
	}

	@Column(name = "COMPANY_ID")
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", mail="
				+ mail + ", sexType=" + sexType + ", companyId=" + companyId
				+ "]";
	}

}
