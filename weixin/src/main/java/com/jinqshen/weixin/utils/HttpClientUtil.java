package com.jinqshen.weixin.utils;
/**
 * 获取access_token
 * @author jinqshen
 *
 */

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.jinqshen.weixin.pojo.AccessToken;
import com.jinqshen.weixin.service.RedisService;

import net.sf.json.JSONObject;
@Component
public class HttpClientUtil {

	@Autowired
	private RedisUtil redisUtil;

	private static final String appID = "wx3b03021c4a6aead2";
	
	private static final String appSecret = "627bb5aaaeedef0be736b342a9e899a2";
	
	private static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=client_credential&"
			+ "appid=appID&secret=appSecret";
	
	public static JSONObject doGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity!=null){
				String result = EntityUtils.toString(entity);
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static JSONObject doPoststr(String url,String outStr){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "utf-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
		    jsonObject =JSONObject.fromObject(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public AccessToken getAccessToken() {
		System.out.println("通过url获取access_token");
		AccessToken accessToken = new AccessToken();
		String url = access_token_url.replace("appID", appID).replace("appSecret", appSecret);
		JSONObject json = doGet(url);
		if(json != null) {
			accessToken.setAccess_token(json.getString("access_token"));
			accessToken.setExpires_in(json.getInt("expires_in"));
			redisUtil.set("access_token", json.getString("access_token"), json.getInt("expires_in"));
		}
		System.out.println("通过url获取access_token" + json.getString("access_token"));
		return accessToken;
	}
	
	public String getAccess_Token() {
		System.out.println("从缓存中读取access_token");
			getAccessToken();
		return redisUtil.get("access_token");
	}

}
