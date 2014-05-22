package com.micmiu.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 网络作者 （一对多的一）
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-6-20 上午9:35:14
 * @version 1.0
 */
@Entity
@Table(name = "DEMO_T_AUTHOR")
public class Author {

	private Long id;

	private String username;

	private String title;

	private String sexType;

	private String description;

	private List<Contact> contacts = new ArrayList<Contact>();

	@Id
	@Column(name = "ID")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
	@OrderBy("id")
	public List<Contact> getContacts() {
		return contacts;
	}

	@Column(name = "SEX_TYPE")
	public String getSexType() {
		return sexType;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSexType(String sexType) {
		this.sexType = sexType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", username=" + username + ", title="
				+ title + ", sexType=" + sexType + ", description="
				+ description + "]";
	}

}
