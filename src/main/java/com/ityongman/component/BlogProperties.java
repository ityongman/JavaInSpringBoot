package com.ityongman.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogProperties {
	@Value("${com.ityongman.blog.name}")
	private String name ;
	@Value("${com.ityongman.blog.title}")
	private String title ;
	@Value("${com.ityongman.blog.desc}")
	private String desc ;
	@Value("${com.ityongman.blog.value}")
	private String value ; 
	@Value("${com.ityongman.blog.number}")
	private Integer number ;
	@Value("${com.ityongman.blog.bignumber}")
	private Long bignumber ;
	@Value("${com.ityongman.blog.lt10}")
	private Integer lt10 ;
	@Value("${com.ityongman.blog.lt20}")
	private Integer lt20 ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public long getBignumber() {
		return bignumber;
	}
	public void setBignumber(Long bignumber) {
		this.bignumber = bignumber;
	}
	public Integer getLt10() {
		return lt10;
	}
	public void setLt10(Integer lt10) {
		this.lt10 = lt10;
	}
	public Integer getLt20() {
		return lt20;
	}
	public void setLt20(Integer lt20) {
		this.lt20 = lt20;
	}
	
}
