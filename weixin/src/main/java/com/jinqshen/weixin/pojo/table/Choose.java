package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
/**
 * 学生选择加入课外锻炼班类，对应数据表student_choose
 * @author jinqshen
 *
 */
public class Choose implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2398566246468270962L;
	
	//选择序号
	private Integer sc_no;
	
	//课外锻炼班号
	private Integer class_no;
	
	//学号
	private Integer student_number;

	public Integer getSc_no() {
		return sc_no;
	}

	public void setSc_no(Integer sc_no) {
		this.sc_no = sc_no;
	}

	public Integer getClass_no() {
		return class_no;
	}

	public void setClass_no(Integer class_no) {
		this.class_no = class_no;
	}

	public Integer getStudent_number() {
		return student_number;
	}

	public void setStudent_number(Integer student_number) {
		this.student_number = student_number;
	}

}
