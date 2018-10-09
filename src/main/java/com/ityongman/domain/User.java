package com.ityongman.domain;
/**
 * day 04
 */
public class User {
	/**
	 * 主键id
	 */
	private Long id ;
	/**
	 * 名字
	 */
	private String name ;
	/**
	 * 年龄
	 */
	private Integer age ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
