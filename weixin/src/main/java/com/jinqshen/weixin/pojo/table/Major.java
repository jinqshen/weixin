package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
/**
 * 学院各专业实体类
 * @author jinqshen
 *
 */
public class Major implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -185433326973938953L;
	
	//专业编号
	private Integer major_no;
	
	//专业名
	private String major_name;
	
	//所属学院编号
	private Integer academy_no;

	public Integer getMajor_no() {
		return major_no;
	}

	public void setMajor_no(Integer major_no) {
		this.major_no = major_no;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public Integer getAcademy_no() {
		return academy_no;
	}

	public void setAcademy_no(Integer academy_no) {
		this.academy_no = academy_no;
	}

}
