package com.ityongman.serivce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ityongman.domain.UserJpa;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long>{
	/**
	 * 根据用户名查询用户信息
	 */
	UserJpa findByName(String name);
	/**
	 * 根据用户名和年龄查询用户信息
	 */
	UserJpa findByNameAndAge(String name , Integer age);
	/**
	 * 
	 */
	@Query("from UserJpa u where u.name = :name")
	UserJpa findUser(@Param("name") String name);
}
