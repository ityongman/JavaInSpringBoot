package com.ityongman.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ityongman.domain.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping(value="/users")
public class UserController {
	static  Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());
	
	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> getUserList() {
		// 处理"/users/"的GET请求，用来获取用户列表 
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递 
		List<User> userList = new ArrayList<>(users.values());
		
		return userList ;
	}
	
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name="user", value="用户详细实体user", required=true, dataType="User")
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		// 处理"/users/"的POST请求，用来创建User 
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数 
		users.put(user.getId(), user);
		
		return "SUCCESS" ;
	}
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id信息获取用户详细信息")
	@ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUser(@PathVariable Long id){
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息 
        // url中的id可通过@PathVariable绑定到函数的参数中
		User user = users.get(id);
		return user ;
	}
	
	@ApiOperation(value="更新用户详细信息" , notes="根据url的id, 指定更新用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="用户ID", required=true , dataType="Long"),
		@ApiImplicitParam(name="user", value="用户详细实体信息", required=true, dataType="User")
	})
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String updateUser(@PathVariable Long id , @ModelAttribute User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		
		users.put(id, u);
		return "SUCCESS";
	}
	
	@ApiOperation(value="删除用户", notes="根据url的id, 指定删除用户信息")
	@ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id){
		// 处理"/users/{id}"的DELETE请求，用来删除User
		users.remove(id);
		
		return "SUCCESS" ;
	}
}
