package com.ityongman.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ityongman.exception.Day06Exception;

@Controller
public class HelloController {
	
	@RequestMapping("/json")
	@ResponseBody
	public String json() throws Exception {
		throw new Day06Exception("发生错误啦!!!");
	}
	
	@RequestMapping("day06")
	@ResponseBody
	public String sixDay() throws Exception {
		throw new Exception() ;
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String helloWorld(){
		Entry entry = null;
		// 务必保证finally会被执行
		try {
		  // 资源名可使用任意有业务语义的字符串
		  entry = SphU.entry("hello World");
		  /**
		  * 被保护的业务逻辑
		  */
		  System.out.println("Good Good Study ...");
		} catch (BlockException e1) {
		  // 资源访问阻止，被限流或被降级
		  // 进行相应的处理操作
		} finally {
		  if (entry != null) {
		    entry.exit();
		  }
		}
		
		List<FlowRule> rules = new ArrayList<FlowRule>();
		FlowRule rule = new FlowRule();
		rule.setResource("HelloWorld");
		// set limit qps to 20
		rule.setCount(20);
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
		
		return "Hello World";
	}
	
	@RequestMapping("/")
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.addAttribute("host", "http://blog.ityongman.com");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}
}
