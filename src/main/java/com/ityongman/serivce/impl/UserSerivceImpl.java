package com.ityongman.serivce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ityongman.serivce.UserService;

@Service
public class UserSerivceImpl implements UserService {
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate ;
	
	@Override
	public void create(String name, Integer age) {
		jdbcTemplate.update("insert into USER(name, age) values(?,?)",name, age) ;
	}

	@Override
	public void deleteByName(String name) {
		jdbcTemplate.update("delete from USER where name = ?", name);
	}

	@Override
	public Integer getAllUsers() {
		return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
	}

	@Override
	public void deleteAllUsers() {
		jdbcTemplate.update("delete from USER");
	}

}
