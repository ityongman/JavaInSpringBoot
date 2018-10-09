package com.ityongman;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ityongman.web.UserController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.* ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.* ;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Ch04ApplicationTest {
	private MockMvc mvc ;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
	@Test
	public void testUserController() throws Exception {
		RequestBuilder request = null ;
		
		request = get("/users/");
		mvc.perform(request).andExpect(status().isOk())
			.andExpect(content().string(equalTo("[]")));
		
		request = post("/users/").param("id", "1")
					.param("name", "ityongman").param("age", "25");
		mvc.perform(request).andExpect(content().string(equalTo("SUCCESS")));
		
		request = get("/users/1");
		mvc.perform(request).andExpect(content().string("{\"id\":1,\"name\":\"ityongman\",\"age\":25}"));
		
		request = put("/users/1").param("name", "ityongman_new").param("age", "22");
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("SUCCESS"));
		
		request = delete("/users/1");
		mvc.perform(request).andExpect(content().string("SUCCESS"));
	}
}
