package com.jinqshen.weixin.utils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * rediscache 工具类
 * 
 */
@Component
public class RedisUtil {

	@Resource(name = "test")
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void set(final String key, String value, long time) {
		stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
	}
	
	
	public String get(final String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	
	
	/*public void remove(final String... keys) {
	    for (String key : keys) {
	    remove(key);
	    }
	}
	*//**
	 * 批量删除key
	 * 
	 * @param pattern
	 *//*
	public void removePattern(final String pattern) {
	    Set<Serializable> keys = redisTemplate.keys(pattern);
	    if (keys.size() > 0)
	    redisTemplate.delete(keys);
	}
	*//**
	 * 删除对应的value
	 * 
	 * @param key
	 *//*
	public void remove(final String key) {
	    if (exists(key)) {
	    redisTemplate.delete(key);
	    }
	}
	*//**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 *//*
	public boolean exists(final String key) {
	    return redisTemplate.hasKey(key);
	}
	*//**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 *//*
	public String get(final String key) {
	    Object result = null;
	    redisTemplate.setValueSerializer(new StringRedisSerializer());
	    ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	    result = operations.get(key);
	    if(result==null){
	        return null;
	    }
	    return result.toString();
		return redisTemplate.opsForValue().get(key);
	}
	*//**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 *//*
	public void set(final String key, String value) {
	    boolean result = false;
	    try {
	    ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	    operations.set(key, value);
	    result = true;
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	    return result;
		redisTemplate.opsForValue().set(key, value);
	}
	*//**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 *//*
	public boolean set(final String key, Object value, Integer expireTime) {
	    boolean result = false;
	    try {
	    ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	    operations.set(key, value);
	    redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	    result = true;
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	    return result;
	    }
	
    public  boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
        e.printStackTrace();
        }
        return result;
    }
    
    public  Map<String,String> hmget(String key) {
        Map<String,String> result =null;
        try {
            result=  redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
        e.printStackTrace();
        }
        return result;
    }*/
}