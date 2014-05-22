package com.micmiu.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @time Create on 2013-7-20 下午2:59:07
 * @version 1.0
 */
@Entity
@Table(name = "T_BANK_ACCOUNT")
public class BankAccount {

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "AMOUNT")
	private Double amount;

	@Column(name = "NOTE")
	private String note;

	// @Version
	@Column(name = "VERSION")
	private Integer version;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BankAccount @" + Integer.toHexString(hashCode()) + "[userId="
				+ userId + ", username=" + username + ", amount=" + amount
				+ ", note=" + note + ", version=" + version + "]";
	}

}
