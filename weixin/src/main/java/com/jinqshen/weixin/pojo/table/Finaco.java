package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
/**
 * 体测结果实体类，对应数据表finaco
 * @author jinqshen
 *
 */
public class Finaco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2759480810336542313L;

	//体测成绩号
	private Integer finaco_no;
	
	//学号
	private Integer student_number;
	
	//体测项目号
	private Integer project_no;
	
	//学期
	private String semester;
	
	//年级
	private String grade;
	
	//测试成绩
	private float test_result;

	public Integer getFinaco_no() {
		return finaco_no;
	}

	public void setFinaco_no(Integer finaco_no) {
		this.finaco_no = finaco_no;
	}

	public Integer getStudent_number() {
		return student_number;
	}

	public void setStudent_number(Integer student_number) {
		this.student_number = student_number;
	}

	public Integer getProject_no() {
		return project_no;
	}

	public void setProject_no(Integer project_no) {
		this.project_no = project_no;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public float getTest_result() {
		return test_result;
	}

	public void setTest_result(float test_result) {
		this.test_result = test_result;
	}
	
}
