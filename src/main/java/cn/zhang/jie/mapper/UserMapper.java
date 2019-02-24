package cn.zhang.jie.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import cn.zhang.jie.beans.User;

@Mapper
public interface UserMapper {
	public User getUserById(Integer id);
	public int insertOneUser(User user);
	public User getUserByName(String name);
	public Set<String> getRoleNamesByUserName(String username);
	public Set<String> getAuthNamesByUsername(String username);
}
