package com.ityongman;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ityongman.domain.primary.Person;
import com.ityongman.domain.second.Message;
import com.ityongman.serivce.primary.PersonRepository;
import com.ityongman.serivce.second.MessageRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Ch08ApplicationTest2 {
	
	@Autowired
	private PersonRepository personRepository ;
	@Autowired
	private MessageRepository messageRepository;
	
	@Test
	public void testJpaRepository() {
		personRepository.deleteAll();
		personRepository.save(new Person("aaa", 10));
		personRepository.save(new Person("bbb", 20));
		personRepository.save(new Person("ccc", 30));
		personRepository.save(new Person("ddd", 40));
		personRepository.save(new Person("eee", 50));

		Assert.assertEquals(5, personRepository.findAll().size());
		
		messageRepository.deleteAll();
		messageRepository.save(new Message("eee", 51));
		messageRepository.save(new Message("fff", 52));
		messageRepository.save(new Message("ggg", 53));

		Assert.assertEquals(3, messageRepository.findAll().size());

	}
	
}
