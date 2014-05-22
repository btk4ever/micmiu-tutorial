package com.micmiu.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-8-19 下午3:14:28
 * @version 1.0
 */
@Entity(name = "DEMO_T_COMPANY")
public class Company {

	private Integer id;

	private String companyName;

	private String companySite;

	private List<Employee> employees = new ArrayList<Employee>();

	@Id
	@Column(name = "ID")
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	@Column(name = "COMPANY_SITE")
	public String getCompanySite() {
		return companySite;
	}

	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "COMPANY_ID")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName
				+ ", companySite=" + companySite + ", employees size="
				+ employees.size() + "]";
	}

}
