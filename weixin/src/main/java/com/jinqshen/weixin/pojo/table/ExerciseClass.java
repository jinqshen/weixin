package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 课外锻炼班实体类，对应数据表exercise_class
 * @author jinqshen
 *
 */
public class ExerciseClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6128292663304620225L;

	//课外锻炼班号
	private Integer class_no;
	
	//课外锻炼项目
	private Integer exercise_project;
	
	//课程介绍
	private String class_introduce;
	
	//开始时间
	private Timestamp begin_time;
	
	//结束时间
	private Timestamp end_time;
	
	//锻炼班容量
	private int capacity;
	
	//地点
	private String site;
	
	//状态
	private String status;

	public Integer getClass_no() {
		return class_no;
	}

	public void setClass_no(Integer class_no) {
		this.class_no = class_no;
	}

	public Integer getExercise_project() {
		return exercise_project;
	}

	public void setExercise_project(Integer exercise_project) {
		this.exercise_project = exercise_project;
	}

	public String getClass_introduce() {
		return class_introduce;
	}

	public void setClass_introduce(String class_introduce) {
		this.class_introduce = class_introduce;
	}

	public Timestamp getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Timestamp begin_time) {
		this.begin_time = begin_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
