package com.ityongman.serivce;

public interface UserService {
	/**
	 * 新增用户
	 */
	void create(String name , Integer age);
	
	/**
	 * 根据用户名删除用户
	 */
	void deleteByName(String name);
	/**
	 * 获取用户总量
	 */
	Integer getAllUsers();
	/**
	 * 删除所有用户
	 */
	void deleteAllUsers();
	
}
