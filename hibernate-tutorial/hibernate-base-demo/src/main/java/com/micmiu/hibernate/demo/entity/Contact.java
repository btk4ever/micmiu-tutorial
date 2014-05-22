package com.micmiu.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 作者{@Author} 的联系方式 一对多的多
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-6-20 上午9:36:16
 * @version 1.0
 */
@Entity
@Table(name = "DEMO_T_CONTACT")
public class Contact {

	private Long id;

	private String type;

	private String details;

	private String other;

	private Author author;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	@Column(name = "DETAILS")
	public String getDetails() {
		return details;
	}

	@Column(name = "OTHER")
	public String getOther() {
		return other;
	}

	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	public Author getAuthor() {
		return author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", type=" + type + ", details=" + details
				+ ", other=" + other + "]";
	}

}
