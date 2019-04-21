package com.jinqshen.weixin.pojo.message;

import java.io.Serializable;
/**
 * 小视频消息类
 * MsgType	消息类型，视频为video,小视频为shortvideo
 * @author jinqshen
 *
 */
public class VideoMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4353418939264342786L;

	//视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;
	
	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;

	public VideoMessage() {
		super();
	}

	public VideoMessage(String mediaId, String thumbMediaId) {
		super();
		MediaId = mediaId;
		ThumbMediaId = thumbMediaId;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
