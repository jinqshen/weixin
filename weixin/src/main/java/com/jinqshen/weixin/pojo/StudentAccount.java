package com.jinqshen.weixin.pojo;

import java.io.Serializable;
/**
 * 学生账号类
 * @author jinqshen
 *
 */
public class StudentAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294403206633509420L;

	private String student_number;
	
	private String student_password;
	
	public StudentAccount() {
		super();
	}

	public StudentAccount(String student_number, String student_password) {
		super();
		this.student_number = student_number;
		this.student_password = student_password;
	}

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public String getStudent_password() {
		return student_password;
	}

	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}

	
}
