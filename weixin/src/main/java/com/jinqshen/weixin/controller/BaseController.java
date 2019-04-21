package com.jinqshen.weixin.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jinqshen.weixin.pojo.AccessToken;
import com.jinqshen.weixin.pojo.Information;
import com.jinqshen.weixin.pojo.StudentAccount;
import com.jinqshen.weixin.service.InformationService;
import com.jinqshen.weixin.service.RedisService;
import com.jinqshen.weixin.service.StudentService;
import com.jinqshen.weixin.utils.CheckUtils;
import com.jinqshen.weixin.utils.HttpClientUtil;
import com.jinqshen.weixin.utils.MessageUtils;
import com.jinqshen.weixin.utils.TextMessageUtil;
import com.jinqshen.weixin.vo.PageBean;

/**
 * 
 * @author jinqshen
 *
 */
@Controller
@RequestMapping("/")
public class BaseController {
	
	@Autowired
	private InformationService informationService;
	
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
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/chart")
	public String chartTest() {
		return "chart";
	}
	
	@RequestMapping("/bookList")
	public ModelAndView layoutTest() {
		ModelAndView mav = new ModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String parameter = request.getParameter("page");
		int currentPage = 1;
		if(parameter != null)
			currentPage = Integer.parseInt(parameter);
		PageBean<Information> pageBean = informationService.findInfoByPage(currentPage);
		mav.addObject("pageBean", pageBean);
		mav.setViewName("bookList");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String deleteInfo(int finacono) {
		informationService.deleteInfoByFinacono(finacono);
		return "数据删除成功";
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Information editInfo(int finacono) {
		Information information = informationService.selectInfoByFinacono(finacono);
		return information;
	}
	
	@RequestMapping("/layout")
	public String layTest() {
		return "layout";
	}
	
	@RequestMapping("/home")
	public String homeTest() {
		return "home";
	}
	
}
