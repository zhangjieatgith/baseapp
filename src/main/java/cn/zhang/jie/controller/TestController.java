package cn.zhang.jie.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@ResponseBody
	@RequestMapping("/test")
	public Object test() {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.debug("debug info");
		logger.info("info info ...");
		User user = new User();
		user.setUserName("abc");
		user.setCreateTime(new Date());
//		return testService.getRoleById(1);
		return testService.insertOneUser(user);
	}
	
	@RequestMapping("/testHtml")
	public String testHtml() {
		return "test";
	}
	
	@RequestMapping("/sample1")
	public String sampleHtml1() {
		return "sample1";
	}
	
	@RequestMapping("/sample2")
	public String sampleHtml2() {
		return "sample2";
	}
	
	@RequestMapping("/sample3")
	public String sampleHtml3() {
		return "sample3";
	}
}
