package com.jinqshen.weixin.vo;
/**
 * 学生信息查询条件类
 * @author jinqshen
 *
 */

import java.io.Serializable;

public class StudentInfoCondition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8859846640796651093L;
	
	public StudentInfoCondition(String student_number, String name, String academy) {
		super();
		this.student_number = student_number;
		this.name = name;
		this.academy = academy;
	}

	//学号
	private String student_number;
	
	//姓名
	private String name;
	
	//所在学院
	private String academy;

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

}
