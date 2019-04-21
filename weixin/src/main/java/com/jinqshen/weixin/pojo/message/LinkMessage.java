package com.jinqshen.weixin.pojo.message;

import java.io.Serializable;
/**
 * 链接消息类
 * MsgType	消息类型，link
 * @author jinqshen
 *
 */
public class LinkMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8071506174545729217L;
	
	//消息标题
	private String Title;
	
	//消息描述
	private String Description;
	
	//消息链接
	private String Url;

	public LinkMessage() {
		super();
	}

	public LinkMessage(String title, String description, String url) {
		super();
		Title = title;
		Description = description;
		Url = url;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
