package cn.zhang.jie.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhang.jie.beans.User;

@RequestMapping("/home")
@Controller
public class HomeController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/doLogin")
	public String loginPage(Map<String,Object> map,HttpServletRequest request) {
		System.out.println("goto login page...");
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
			session.setTimeout(45*1000);		//设置session过期时间
			if (SecurityUtils.getSubject().getPrincipal() != null){
//				return "index";
				return "redirect:"+ "/home/index";
			}else {
				map.put("msg", "用戶名或密码错误");
				return "login";
			}
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}
	
	@RequestMapping("/index")
	public String goIndex(Map<String,Object> map) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipals().getPrimaryPrincipal();
		System.out.println("index:subject is remember me ? " + subject.isRemembered());
		map.put("auths", user.getAuths());
		map.put("roles", user.getRoles());
		return "index";
	}
	
	@RequestMapping("/goIndex222")
	public String goIndex222() {
		return "index222";
	}
}
