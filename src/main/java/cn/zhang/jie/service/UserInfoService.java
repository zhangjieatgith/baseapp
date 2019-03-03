package cn.zhang.jie.service;

import java.util.List;
import java.util.Map;

import cn.zhang.jie.beans.User;


public interface UserInfoService {

	public List<Map<String,Object>> getUserInfo(String userName);
	
	public Map<String,Object> selectUsers();
	
	public Object initUserPass(User user);
	public Object getAllAuths();
	public Object getAllRoles();
	public Object getAuthsForOneRoleByTree(String roleId);
	
	
	public Object updateAuthOk(String roleId,String [] authIds);
}
