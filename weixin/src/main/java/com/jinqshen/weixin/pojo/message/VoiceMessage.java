package com.jinqshen.weixin.pojo.message;

import java.io.Serializable;
/**
 * 声音消息类
 * MsgType	消息类型，voice
 * @author jinqshen
 *
 */
public class VoiceMessage extends BaseMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8968446817218692232L;

	//语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
	private String MediaId;
	
	//语音格式：amr
	private String Format;
	
	//语音识别结果，UTF8编码（开启语音识别功能后才会有）
	private String Recognition;

	public VoiceMessage() {
		super();
	}

	public VoiceMessage(String mediaId, String format, String msgId, String recognition) {
		super();
		MediaId = mediaId;
		Format = format;
		MsgId = msgId;
		Recognition = recognition;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	
}
