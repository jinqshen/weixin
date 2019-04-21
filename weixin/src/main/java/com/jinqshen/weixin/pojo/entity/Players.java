package com.jinqshen.weixin.pojo.entity;

import java.io.Serializable;
/**
 * Players实体类
 * @author jinqshen
 *
 */
public class Players implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7898429648698561231L;
	
	private Integer id;
	
	private String name;
	
	private String location;
	
	private Float kda;
	
	private String team;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Float getKda() {
		return kda;
	}

	public void setKda(Float kda) {
		this.kda = kda;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
