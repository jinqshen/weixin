package com.jinqshen.weixin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jinqshen.weixin.pojo.Const;

public class PageFilter implements Filter {

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		String uri = request.getRequestURI();
		if(uri.contains("manage")){
			if(request.getSession().getAttribute(Const.LoginManageUser.getValue()) == null){
				if(containsKey(uri) || containsSuffix(uri)) {
					filterChain.doFilter(srequest, sresponse);
					return;
				}else {
					HttpServletResponse response = (HttpServletResponse) sresponse;
					String url = "/manage/login";
					response.sendRedirect(url);
				}
			}else {
				filterChain.doFilter(srequest, sresponse);
			}
		}else {
			if(request.getSession().getAttribute(Const.LoginUser.getValue()) == null) {
				if(containsKey(uri) || containsSuffix(uri)) {
					filterChain.doFilter(srequest, sresponse);
					return;
				}else {
					HttpServletResponse response = (HttpServletResponse) sresponse;
					String url = "/student/loginPage";
					response.sendRedirect(url);
				}
			}else {
				filterChain.doFilter(srequest, sresponse);
			}
		}


	}
	
	/**
	 * 判断是否为静态资源
	 * @param url
	 * @return
	 */
	private boolean containsSuffix(String url) {
		if (url.endsWith(".js")
				|| url.endsWith(".css")
				|| url.endsWith(".jpg")
				|| url.endsWith(".jpeg")
				|| url.endsWith(".gif")
				|| url.endsWith(".png")
				|| url.endsWith(".html")
				|| url.endsWith(".eot")
				|| url.endsWith(".svg")
				|| url.endsWith(".ttf")
				|| url.endsWith(".woff")
				|| url.endsWith(".ico")
				|| url.endsWith(".woff2")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对于注册页面，登录页面进行判断
	 * @param url
	 * @return
	 */
	private boolean containsKey(String url) {
		if(url.contains("/student/login")
			|| url.contains("/student/register")
			|| url.contains("/wx")
			|| url.contains("/student/loginPage")
			|| url.contains("/manage/downloadFinacoTestResultExcel")
			|| url.contains("manage/downloadStudentInfoExcel")
			|| url.contains("/manage/exportJoinedStudentInfos")
			|| url.contains("/manage/login")
			|| url.contains("/manage/loginValidate"))
			return true;
		else {
			return false;
		}
			
	}
}
