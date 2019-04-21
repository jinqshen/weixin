package com.jinqshen.weixin.pojo.message;

import java.io.Serializable;

/**
 * 
 * @author jinqshen
 *
 */
public class BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5518671317435306583L;
	
	//接受者
	protected String ToUserName;
	
	//发送者
	protected String FromUserName;
	
	//消息创建时间
	protected long CreateTime;
	
	//消息类型
	protected String MsgType;
	
	//消息id，64位整型
	protected String MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
}
