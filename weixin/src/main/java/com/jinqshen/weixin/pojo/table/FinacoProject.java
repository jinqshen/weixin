package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;
/**
 * 体测项目实体类，对应数据表finaco_project
 * @author jinqshen
 *
 */
public class FinacoProject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2819348619598961036L;
	
	//体测项目ID
	private Integer project_no;
	
	//体测项目名
	private String project_name;

	public Integer getProject_no() {
		return project_no;
	}

	public void setProject_no(Integer project_no) {
		this.project_no = project_no;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

}
