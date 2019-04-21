package com.jinqshen.weixin.service;

public interface RedisService {

	public void set(final String key, String value, long time);
	
	public String get(final String key);
}
