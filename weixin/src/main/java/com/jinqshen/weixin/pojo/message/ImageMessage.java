package com.jinqshen.weixin.pojo.message;

import java.io.Serializable;
/**
 * 图片消息类
 * MsgType	消息类型，image
 * @author jinqshen
 *
 */
public class ImageMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7386973238652646007L;

	//图片链接（由系统生成）
	private String PicUrl;
	
	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;

	public ImageMessage() {
		super();
	}

	public ImageMessage(String picUrl, String mediaId) {
		super();
		PicUrl = picUrl;
		MediaId = mediaId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
