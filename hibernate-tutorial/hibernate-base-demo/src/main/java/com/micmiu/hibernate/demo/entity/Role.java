package com.micmiu.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "DEMO_T_ROLE")
public class Role extends IdEntity {

	private String roleName;

	private List<Permssion> permssions = new ArrayList<Permssion>();

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return roleName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	public List<Permssion> getPermssions() {
		return permssions;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setPermssions(List<Permssion> permssions) {
		this.permssions = permssions;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
