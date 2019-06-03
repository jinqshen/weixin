package com.jinqshen.weixin.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinqshen.weixin.utils.CheckUtils;
import com.jinqshen.weixin.utils.MessageUtils;
import com.jinqshen.weixin.utils.TextMessageUtil;

/**
 * 
 * @author jinqshen
 *
 */
@Controller
@RequestMapping("/")
public class BaseController {
	
	@RequestMapping("/wx1")
	public void vaildate(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		System.out.println("success");
		boolean flag = CheckUtils.checkSignature(signature, timestamp, nonce);
		
		try {
			out = response.getWriter();
			if(flag) {
				out.write(echostr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	@RequestMapping("/wx")
	public void message(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> messageMap = MessageUtils.xmlToMap(request);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		String toUserName = messageMap.get("ToUserName");
		String fromUserName = messageMap.get("FromUserName");
		String createTime = messageMap.get("CreateTime");
		String msgType = messageMap.get("MsgType");
		String content = messageMap.get("Content");
		String message = "";
		if(msgType.equals("text")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, content);
		}
		if(msgType.equals("image")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, "图片消息");
		}
		if(msgType.equals("voice")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, "声音消息");
		}
		if(msgType.equals("video")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, "视频消息");
		}
		if(msgType.equals("shortvideo")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, "小视频消息");
		}
		if(msgType.equals("location")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, "地理消息");
		}
		if(msgType.equals("link")) {
			TextMessageUtil textMessageUtil = new TextMessageUtil();
			message = textMessageUtil.initMessage(fromUserName, toUserName, "链接消息");
		}
		System.out.println(message);
		try {
			out = response.getWriter();
			out.write(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
	}
}
