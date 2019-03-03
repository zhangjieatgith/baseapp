package cn.zhang.jie.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.service.UserInfoService;

@RequestMapping("/home")
@Controller
public class HomeController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object loginPage(Map<String,Object> map,HttpServletRequest request) {
		System.out.println("goto login page...");
		JSONObject result = new JSONObject();
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken();
			token.setUsername(request.getParameter("username"));
			token.setPassword(request.getParameter("password").toCharArray());
			String rememberme = request.getParameter("rememberme");
			if(rememberme != null && rememberme.equals("on")) {
				token.setRememberMe(true);
			}
			System.out.println("rememberme ? " + rememberme);
			subject.login(token);
			Session session = subject.getSession();
			System.out.println("session id : " + session.getId());
			session.setTimeout(45*100000);		//设置session过期时间
			if (SecurityUtils.getSubject().getPrincipal() != null){
//				return "index";
				result.put("code", 200);
				result.put("msg", "/home/index");
				return result;
//				return "redirect:"+ "/home/index";
			}else {
				result.put("code", 400);
				result.put("msg", "用戶名或密码错误");
//				map.put("msg", "");
//				return "login";
				return result;
			}
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			result.put("code", 401);
			result.put("msg", "未知错误");
//			return "login";
			return result;
		}
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}
	
	@RequestMapping("/index")
	public String goIndex(Map<String,Object> map) throws JsonProcessingException {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipals().getPrimaryPrincipal();
		System.out.println("index:subject is remember me ? " + subject.isRemembered());
		map.put("auths", user.getAuths());
		map.put("roles", user.getRoles());
		ObjectMapper objectMapper = new ObjectMapper();
		String userAuthInfo = objectMapper.writeValueAsString(userInfoService.getUserInfo(user.getUserName()));
		System.out.println("userAuthInfo : " + userAuthInfo);
		map.put("userAuthInfo", userAuthInfo);
		return "index";
	}
	
	@RequestMapping("/goIndex222")
	public String goIndex222() {
		return "index222";
	}
}
