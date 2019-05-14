package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
import java.sql.Date;
/**
 * 学生信息实体类，对应数据表student_info
 * @author jinqshen
 *
 */
public class StudentInfo{
	
	//学号
	private Integer student_number;
	
	//姓名
	private String name;
	
	//性别
	private String sex;
	
	//专业
	private String major;
	
	//所在学院
	private String academy;
	
	//出生年月
	private Date birth;
	
	//身份证号
	private String id_number;

	public Integer getStudent_number() {
		return student_number;
	}

	public void setStudent_number(Integer student_number) {
		this.student_number = student_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

}
