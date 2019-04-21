package com.jinqshen.weixin.pojo;
/**
 * 常量枚举类
 * @author jinqshen
 *
 */
public enum Const {

	LoginUser("loginUser");
	
	private String value;
	
	private Const(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
