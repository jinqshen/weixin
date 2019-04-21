package com.jinqshen.weixin.pojo.menu;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * 自定义菜单类
 * @author jinqshen
 *
 */
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2008765763994526792L;

	//父按钮
	@SerializedName("button")
	private List<FatherButton> fatherButtons;

	public List<FatherButton> getFatherButtons() {
		return fatherButtons;
	}

	public void setFatherButtons(List<FatherButton> fatherButtons) {
		this.fatherButtons = fatherButtons;
	}
	
}
