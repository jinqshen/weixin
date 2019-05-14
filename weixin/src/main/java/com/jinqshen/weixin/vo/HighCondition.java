package com.jinqshen.weixin.vo;

public class HighCondition {

	private String conditionName;
	
	private String conditionValue;
	
	private String andor;
	
	public HighCondition(String conditionName, String conditionValue, String andor) {
		super();
		this.conditionName = conditionName;
		this.conditionValue = conditionValue;
		this.andor = andor;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public String getAndor() {
		return andor;
	}

	public void setAndor(String andor) {
		this.andor = andor;
	}
	
	
}
