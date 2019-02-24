package cn.zhang.jie;

import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zhang.jie.beans.User;
import cn.zhang.jie.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpBaseappApplicationTests {

	@Autowired
	RedisTemplate<Object, Object> jsonRedisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	UserMapper userMapper;
	
	@Test
	public void contextLoads() {
//		User user = userMapper.getUserById(1);
//		User user = userMapper.getUserByName("zhangsan");
//		System.out.println(user);
//		Set<String> set = userMapper.getRoleNamesByUserName("zhangsan");
		Set<String> set = userMapper.getAuthNamesByUsername("zhangsan");
		System.out.println(set);
	}
}
