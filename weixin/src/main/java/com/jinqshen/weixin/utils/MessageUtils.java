package com.jinqshen.weixin.utils;
/**
 * xml消息转map类
 * @author jinqshen
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtils {

	
	public static HashMap<String, String> xmlToMap(HttpServletRequest request){
		HashMap<String,String> map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		InputStream in = null;
		try {
			in = request.getInputStream();
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for(Element element : list) {
				map.put(element.getName(), element.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
