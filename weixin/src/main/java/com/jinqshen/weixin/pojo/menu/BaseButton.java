package com.jinqshen.weixin.pojo.menu;

import java.io.Serializable;
/**
 * 基础按钮类
 * @author jinqshen
 *
 */
public class BaseButton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9086489604455760409L;
	
	//菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
	private String type;
	
	//菜单标题，不超过16个字节，子菜单不超过60个字节
	private String name;
	
	//菜单KEY值，用于消息接口推送，不超过128字节
	private String key;
	
	//网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
	private String url;
	
	//调用新增永久素材接口返回的合法media_id
	private String media_id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	
}
