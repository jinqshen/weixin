package com.jinqshen.weixin.pojo.message;

import java.io.Serializable;
/**
 * 地理消息类
 * MsgType	消息类型，location
 * @author jinqshen
 *
 */
public class LocationMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1237838556550950024L;

	//地理位置纬度
	private String Location_X;
	
	//地理位置经度
	private String Location_Y;
	
	//地图缩放大小
	private String Scale;
	
	//地理位置信息
	private String Label;

	public LocationMessage() {
		super();
	}

	public LocationMessage(String location_X, String location_Y, String scale, String label) {
		super();
		Location_X = location_X;
		Location_Y = location_Y;
		Scale = scale;
		Label = label;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

}
