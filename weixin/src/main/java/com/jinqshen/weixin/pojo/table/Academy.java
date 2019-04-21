package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
/**
 * 学院实体类
 * @author jinqshen
 *
 */
public class Academy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 300222618639440394L;
	
	//学院编号
	private Integer academy_no;
	
	//学院名
	private String academy_name;

	public Integer getAcademy_no() {
		return academy_no;
	}

	public void setAcademy_no(Integer academy_no) {
		this.academy_no = academy_no;
	}

	public String getAcademy_name() {
		return academy_name;
	}

	public void setAcademy_name(String academy_name) {
		this.academy_name = academy_name;
	}

}
