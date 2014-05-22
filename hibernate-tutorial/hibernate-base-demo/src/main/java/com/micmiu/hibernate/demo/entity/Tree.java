package com.micmiu.hibernate.demo.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 树形结构示例
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Entity
@Table(name = "DEMO_T_TREE")
public class Tree {

	public Tree() {
	}

	public Tree(String name) {
		this.name = name;
	}

	private int id;

	private String name;
	// 父节点
	private Tree parent;
	// 子节点
	private Set<Tree> children = new LinkedHashSet<Tree>();

	@Id
	@Column(name = "ID")
	@GeneratedValue
	public int getId() {
		return id;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return name;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_ID")
	public Tree getParent() {
		return parent;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	@OrderBy("id")
	public Set<Tree> getChildren() {
		return children;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}

	public void setChildren(Set<Tree> children) {
		this.children = children;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}