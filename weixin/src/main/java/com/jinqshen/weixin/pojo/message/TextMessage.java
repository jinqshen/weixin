package com.jinqshen.weixin.pojo.message;
/**
 * 文本消息类
 * MsgType	消息类型，text
 * @author jinqshen
 *
 */
public class TextMessage extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4391313081143250865L;
	
	//文本消息内容
	private String Content;
	
	public TextMessage() {
		super();
	}

	public TextMessage(String toUserName, String fromUserName, long createTime, String msgType, String content) {
		this.ToUserName = toUserName;
		this.FromUserName = fromUserName;
		this.CreateTime = createTime;
		this.MsgType = msgType;
		this.Content = content;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

}
