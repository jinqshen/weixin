package com.jinqshen.weixin.utils;

import java.util.Arrays;

/**
 * Token检查类
 * @author jinqshen
 *
 */
public class CheckUtils {

	public static final String token = "jinqshen";
	
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] str = new String[]{token, timestamp, nonce};
		Arrays.sort(str);
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < str.length;i++) {
			buffer.append(str[i]);
		}
		String encode = Sha1.encode(buffer.toString());
		return encode.equals(signature);
		
	}
}
