package com.jinqshen.weixin.pojo.menu;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * 父按钮类
 * @author jinqshen
 *
 */
public class FatherButton extends BaseButton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3619546006663100682L;

	@SerializedName("sub_button")
	private List<SonButton> sonButtons;

	public List<SonButton> getSonButtons() {
		return sonButtons;
	}

	public void setSonButtons(List<SonButton> sonButtons) {
		this.sonButtons = sonButtons;
	}
	
}
