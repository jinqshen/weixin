package com.jinqshen.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jinqshen.weixin.pojo.Const;
import com.jinqshen.weixin.pojo.ExceptionMsg;
import com.jinqshen.weixin.pojo.ResponseData;
import com.jinqshen.weixin.pojo.StudentAccount;
import com.jinqshen.weixin.service.StudentService;

/**
 * 学生控制层
 * @author jinqshen
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	/**
	 * 学生登录接口
	 * @param studentAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseData login(StudentAccount studentAccount) {
		try {
			StudentAccount loginStudentAccount = studentService.findStudentAccount(studentAccount.getStudent_number(), studentAccount.getStudent_password());
			if(loginStudentAccount == null) {
				return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
			}
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = servletRequestAttributes.getRequest();
			request.getSession().setAttribute(Const.LoginUser.getValue(), loginStudentAccount);
			return new ResponseData(ExceptionMsg.SUCCESS, "/chart");
		} catch (Exception e) {
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseData register(StudentAccount studentAccount) {
		try {
			studentService.registStudentAccount(studentAccount.getStudent_number(), studentAccount.getStudent_password());
			return new ResponseData(ExceptionMsg.SUCCESS, "/login");
		} catch (Exception e) {
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}
	
}
