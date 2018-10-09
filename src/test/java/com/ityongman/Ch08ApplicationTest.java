package com.ityongman;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ityongman.domain.UserJpa;
import com.ityongman.serivce.UserJpaRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Ch08ApplicationTest {
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate primaryTemplate ;
	
	@Autowired
	@Qualifier("secondJdbcTemplate")
	private JdbcTemplate secondTemplate ;
	
	@Before
	public void setUp() {
		primaryTemplate.update("DELETE FROM USER");
		secondTemplate.update("DELETE FROM USER");
	}
	
	@Test
	public void testMultipleDataSource() {
		//往主数据源中插入两条数据
		primaryTemplate.update("insert into user(name, age) values (?,?)", "aaaa", 20);
		primaryTemplate.update("insert into user(name, age) values (?,?)","bbbb", 21);
		
		//往副数据源插入一条数据
		secondTemplate.update("insert into user(name, age) values (?,?)","cccc", 22);
		
		//查询主数据源是否有两条数据
		Assert.assertEquals("2", primaryTemplate.queryForObject("select count(1) from user", String.class));
		
		//查询副数据源是否有一条数据源
		Assert.assertEquals("1", secondTemplate.queryForObject("select count(1) from user", String.class));
	}
	
	
	@Test
	public void testUserJpaRepository() {
		userJpaRepository.deleteAll();
		
		userJpaRepository.save(new UserJpa("AAA", 1));
		userJpaRepository.save(new UserJpa("BBB", 2));
		userJpaRepository.save(new UserJpa("CCC", 3));
		userJpaRepository.save(new UserJpa("DDD", 4));
		userJpaRepository.save(new UserJpa("EEE", 5));
		userJpaRepository.save(new UserJpa("FFF", 6));
		userJpaRepository.save(new UserJpa("GGG", 7));
		userJpaRepository.save(new UserJpa("HHH", 8));
		userJpaRepository.save(new UserJpa("III", 9));
		userJpaRepository.save(new UserJpa("JJJ", 10));
		
		//测试findAll方法
		Assert.assertEquals(10, userJpaRepository.findAll().size());
		
		//测试findByName方法
		Assert.assertEquals(8, userJpaRepository.findByName("HHH").getAge().intValue());
		
		//测试findUser
		Assert.assertEquals(10, userJpaRepository.findUser("JJJ").getAge().intValue());
		
		//测试findByNameAndAge
		Assert.assertEquals("III", userJpaRepository.findByNameAndAge("III", 9).getName());
		
		//测试删除姓名为 "AAA" 的UserJpa
		userJpaRepository.delete(userJpaRepository.findByName("AAA"));
		
		//测试findAll, 查询所有记录, 验证上面的删除是否成功
		Assert.assertEquals(9, userJpaRepository.findAll().size());
		
	}
	
}
