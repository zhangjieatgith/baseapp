package cn.zhang.jie.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.zhang.jie.beans.User;

@Mapper
public interface UserMapper {
	public User getUserById(Integer id);
	public int insertOneUser(User user);
	public User getUserByName(String name);
	public Set<String> getRoleNamesByUserName(String username);
	public Set<String> getAuthNamesByUsername(String username);
	
	public List<Map<String,Object>> selectUsers();
	public int initUserPass(User user);
	
	public List<Map<String,Object>> getAllAuths();
	public List<Map<String,Object>> getAllRoles();
	public List<Map<String,Object>> getAuthsForOneRoleByTree(@Param("roleId") String roleId);
	
	public List<Map<String,Object>> getUserAuthInfo (String username);
	
	public int deleteMapRoleAuthByAuthIds(@Param("roleId")String roleId);
	public int insertMultiAuthForOneRole(@Param("roleId") String roleId,@Param("authIds") String[] authIds);
}
