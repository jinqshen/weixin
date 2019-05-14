package com.jinqshen.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.jinqshen.weixin.vo.Transcript;
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
	public void contextLoads() {
		String string = stringRedisTemplate.opsForValue().get("oop");
		String access_Token = httpClientUtil.getAccess_Token();
		System.out.println("access_token:" + string);
		System.out.println(access_Token);
	}
	
	@Test
	public void jsonTest() {
		SonButton sonButton1 = new SonButton();
		sonButton1.setName("后台管理入口");
		sonButton1.setType("view");
		sonButton1.setUrl("http://www.jinqshen.com/manage/login");
		SonButton sonButton2 = new SonButton();
		sonButton2.setName("学生入口");
		sonButton2.setType("view");
		sonButton2.setUrl("http://www.jinqshen.com/student/loginPage");
		ArrayList<SonButton> sonButtons = new ArrayList<>();
		sonButtons.add(sonButton1);
		sonButtons.add(sonButton2);
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

	@Test
	public void mapToJson(){
		HashMap<String, List<Transcript>> map = new HashMap<>();
		ArrayList<Transcript> list = new ArrayList<>();
		list.add(new Transcript(2015214208,"沈进强","跑步",50,"5'32","一般"));
		list.add(new Transcript(2015214207,"柯傻逼","跑步",50,"5'32","一般"));
		list.add(new Transcript(2015214206,"塔斯克","跑步",50,"5'32","一般"));
		map.put("2018-2019",list);
		Gson gson = new Gson();
		String s = gson.toJson(map);
		System.out.println(s);
	}

}

