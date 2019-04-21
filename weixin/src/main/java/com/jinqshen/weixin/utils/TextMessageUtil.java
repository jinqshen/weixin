package com.jinqshen.weixin.utils;

import java.util.Date;

import com.jinqshen.weixin.pojo.message.TextMessage;
import com.thoughtworks.xstream.XStream;

public class TextMessageUtil implements BaseMessageUtil<TextMessage> {

	public String messageToXml(TextMessage message) {
		XStream xStream = new XStream();
		xStream.alias("xml", message.getClass());
		return xStream.toXML(message);
	}

	public String initMessage(String fromUserName, String toUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setMsgType("text");
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setContent("欢迎关注jinqshen的测试公众号,您发送的信息是:" + content);
		return messageToXml(textMessage);
	}

}
