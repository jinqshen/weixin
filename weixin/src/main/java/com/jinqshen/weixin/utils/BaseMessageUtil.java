package com.jinqshen.weixin.utils;
/**
 * 基本消息转xml接口
 * @author jinqshen
 *
 */
public interface BaseMessageUtil<T> {

	public String messageToXml(T t);
	
	public String initMessage(String fromUserName,String toUserName, String content);

}
