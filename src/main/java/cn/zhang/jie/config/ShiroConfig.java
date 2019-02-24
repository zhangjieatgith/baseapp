package cn.zhang.jie.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	@Autowired
	private MyShiroRealm shiroRealm;
	
	/**
	 * web过滤器，处理拦截资源文件
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//登录页面
		shiroFilterFactoryBean.setLoginUrl("/home/login");
		//登录成功后页面
//		shiroFilterFactoryBean.setSuccessUrl("/home/index");
		//未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/home/403");
		//拦截器
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		//anon表示可以匿名访问，authc表示需要认证通过才可以访问
		filterChainDefinitionMap.put("/home/doLogin", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/easyui/**", "anon");
		filterChainDefinitionMap.put("/home/logout", "logout");
		//将需要认证的放在最下面
		filterChainDefinitionMap.put("/home/index", "user");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm);
		return securityManager;
	}
	
	
}
