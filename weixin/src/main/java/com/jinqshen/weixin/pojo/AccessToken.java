package com.jinqshen.weixin.pojo;

import java.io.Serializable;

/**
 * access_token类
 * @author jinqshen
 *
 */
public class AccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6666733865845590215L;

	//获取到的凭证access_token
	private String access_token;
	
	//凭证有效时间
	private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
