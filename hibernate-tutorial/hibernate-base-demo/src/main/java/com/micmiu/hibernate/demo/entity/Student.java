package com.micmiu.hibernate.demo.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 * Description:
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-6 下午2:40:08
 * @version 1.0
 */
@Entity
@Table(name = "DEMO_T_STUDENT")
public class Student extends IdEntity {

	@Column(name = "NAME")
	private String name;

	@Column(name = "BIRTHDAY")
	private Date birthday;

	private Collection<Course> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@ManyToMany
	@JoinTable(name = "DEMO_R_S2C", joinColumns = { @JoinColumn(name = "SID") }, inverseJoinColumns = { @JoinColumn(name = "CID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", birthday=" + birthday + "]";
	}

}
