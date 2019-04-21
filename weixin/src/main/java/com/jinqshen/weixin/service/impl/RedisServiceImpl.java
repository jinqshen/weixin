package com.jinqshen.weixin.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.jinqshen.weixin.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void set(String key, String value, long time) {
		stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
		
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	
}
