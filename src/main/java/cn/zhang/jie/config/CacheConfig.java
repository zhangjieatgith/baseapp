package cn.zhang.jie.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration
public class CacheConfig {
	
//	@Bean(name="redisCacheManager")
	public CacheManager initRedisCacheManager(@Autowired RedisTemplate<String,Object> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		//设置超时时间为1分钟，单位为秒
		cacheManager.setDefaultExpiration(90);
		List<String> cacheNames = new ArrayList<>();
		cacheNames.add("redisCacheManager");
		cacheManager.setCacheNames(cacheNames);
		return cacheManager;
	}
}
