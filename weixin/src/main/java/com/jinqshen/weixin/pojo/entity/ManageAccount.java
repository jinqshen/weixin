package com.jinqshen.weixin.pojo.entity;

import java.io.Serializable;
/**
 * 管理账号类
 * @author jinqshen
 *
 */
public class ManageAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4199528923287356107L;
	
	private String manageaccount;
	
	private String managepassword;

	public String getManageaccount() {
		return manageaccount;
	}

	public void setManageaccount(String manageaccount) {
		this.manageaccount = manageaccount;
	}

	public String getManagepassword() {
		return managepassword;
	}

	public void setManagepassword(String managepassword) {
		this.managepassword = managepassword;
	}

}
