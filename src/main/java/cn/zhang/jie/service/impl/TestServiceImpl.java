package cn.zhang.jie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.mapper.UserMapper;
import cn.zhang.jie.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Cacheable(value="redisCacheManager",key="'cache_user_'+#id")
	public User getRoleById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	@CachePut(value="redisCacheManager",key="'cache_user_'+#result.id")
	public User insertOneUser(User user) {
		userMapper.insertOneUser(user);
		int i = 1/0;
		return user;
	}

}
