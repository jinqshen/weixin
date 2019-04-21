package com.jinqshen.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jinqshen.weixin.mapper.StudentMapper;
import com.jinqshen.weixin.pojo.StudentAccount;
import com.jinqshen.weixin.pojo.menu.FatherButton;
import com.jinqshen.weixin.pojo.menu.Menu;
import com.jinqshen.weixin.pojo.menu.SonButton;
import com.jinqshen.weixin.service.RedisService;
import com.jinqshen.weixin.utils.HttpClientUtil;

import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan(basePackages = "com.jinqshen.weixin")
public class WeixinApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	@Resource
	private RedisService redisService;
	
	@Test
	public void mapperTest() {
		StudentAccount findAccount = studentMapper.findAccount("2015214208", "1ae3ea8b05549372f61954df007e85cf");
		System.out.println(findAccount.getStudent_number());
		System.out.println(findAccount.getStudent_password());
	}
	
	
	@Test
	public void contextLoads() {
		String string = stringRedisTemplate.opsForValue().get("oop");
		String access_Token = httpClientUtil.getAccess_Token();
		System.out.println("access_token:" + string);
		System.out.println(access_Token);
	}
	
	@Test
	public void jsonTest() {
		SonButton sonButton1 = new SonButton();
		sonButton1.setName("后台管理首页");
		sonButton1.setType("view");
		sonButton1.setUrl("http://www.jinqshen.com/home");
		SonButton sonButton2 = new SonButton();
		sonButton2.setName("搜狗搜索");
		sonButton2.setType("view");
		sonButton2.setUrl("http://www.soso.com/");
		SonButton sonButton3 = new SonButton();
		sonButton3.setName("体测成绩查询");
		sonButton3.setType("view");
		sonButton3.setUrl("http://www.jinqshen.com/login");
		ArrayList<SonButton> sonButtons = new ArrayList<>();
		sonButtons.add(sonButton1);
		sonButtons.add(sonButton2);
		sonButtons.add(sonButton3);
		FatherButton fatherButton1 = new FatherButton();
		fatherButton1.setType("click");
		fatherButton1.setName("每日推荐");
		fatherButton1.setKey("V001_TODAY");
		FatherButton fatherButton2 = new FatherButton();
		fatherButton2.setName("菜单");
		fatherButton2.setSonButtons(sonButtons);
		Menu menu = new Menu();
		ArrayList<FatherButton> fatherButtons = new ArrayList<>();
		fatherButtons.add(fatherButton1);
		fatherButtons.add(fatherButton2);
		menu.setFatherButtons(fatherButtons);
		String json = new Gson().toJson(menu);
		System.out.println(json);
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		String access_Token = redisService.get("access_token");
		String realurl = url.replace("ACCESS_TOKEN", access_Token);
		JSONObject doPoststr = HttpClientUtil.doPoststr(realurl, json);
		System.out.println(doPoststr);
	}

}

