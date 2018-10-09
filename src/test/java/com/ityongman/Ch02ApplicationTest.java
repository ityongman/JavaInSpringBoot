package com.ityongman;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ityongman.component.BlogProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Ch02ApplicationTest {
	private static final Log log = LogFactory.getLog(Ch02ApplicationTest.class);
	
	@Autowired
	private BlogProperties blogProperties ;
	
	@Test
	public void testBlog() {
		Assert.assertEquals(blogProperties.getName(), "ityongman");
		Assert.assertEquals(blogProperties.getTitle(), "yewandemty");
		System.out.println(blogProperties.getDesc());
		
		System.out.println(blogProperties.getNumber());
		System.out.println(blogProperties.getBignumber());
		System.out.println(blogProperties.getValue());
		System.out.println(blogProperties.getLt10());
		System.out.println(blogProperties.getLt20());
	}
}
