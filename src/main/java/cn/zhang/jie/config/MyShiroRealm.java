package cn.zhang.jie.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.mapper.UserMapper;
import cn.zhang.jie.service.UserInfoService;
import cn.zhang.jie.utils.Md5Util;


@Component
public class MyShiroRealm extends AuthorizingRealm{

	private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private UserMapper userMapper;
	
    @Override
    public String getName() {
        return "myShiroRealm";
    }
	
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User shiroUser = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(shiroUser.getRoles());
        info.setStringPermissions(shiroUser.getAuths());
        return info;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		logger.info("开始认证登录信息");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String account = token.getUsername();
		String password = "";
		if(token.getPassword() != null) {
			password = String.valueOf(token.getPassword());
		}
		User user = userMapper.getUserByName(account);
		if(user == null) {
			throw new RuntimeException("用户不存在");
		}
        if (!Md5Util.md5(user.getUserName() + password).equals(user.getPassword())) {
            throw new RuntimeException("密码错误!");
        }
        //读取用户的URL和角色
        User shiroUser = new User();
        shiroUser.setId(user.getId());
        shiroUser.setUserName(user.getUserName());
        shiroUser.setAuths(userMapper.getAuthNamesByUsername(account)); 	//由数据库查出
        shiroUser.setRoles(userMapper.getRoleNamesByUserName(account)); 	//由数据库查出
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, password, getName());
	}

}
