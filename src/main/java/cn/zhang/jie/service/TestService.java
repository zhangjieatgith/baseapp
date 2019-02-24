package cn.zhang.jie.service;

import cn.zhang.jie.beans.User;

public interface TestService {

	public User getRoleById(Integer id);
	public User insertOneUser(User user);
}
