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
	private Integer finaco_no;
	
	//体测项目名
	private String name;

	public Integer getFinaco_no() {
		return finaco_no;
	}

	public void setFinaco_no(Integer finaco_no) {
		this.finaco_no = finaco_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
