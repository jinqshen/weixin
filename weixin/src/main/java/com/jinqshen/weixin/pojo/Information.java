package com.jinqshen.weixin.pojo;

import java.io.Serializable;

/**
 * 
 * @author jinqshen
 *
 */
public class Information implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1414076254904354441L;

	private Integer finacono;
	
	private String studentnumber;
	
	private String name;
	
	private String sex;
	
	private String major;
	
	private String academy;
	
	private String project;
	
	private Float grade;
	
	private Boolean isqualified;

	public Integer getFinacono() {
		return finacono;
	}

	public void setFinacono(Integer finacono) {
		this.finacono = finacono;
	}

	public String getStudentnumber() {
		return studentnumber;
	}

	public void setStudentnumber(String studentnumber) {
		this.studentnumber = studentnumber;
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public Boolean isIsqualified() {
		return isqualified;
	}

	public void setIsqualified(boolean isqualified) {
		this.isqualified = isqualified;
	}
}
