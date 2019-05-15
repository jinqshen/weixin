package com.jinqshen.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import com.jinqshen.weixin.pojo.table.Academy;
import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.pojo.table.OrderFinaco;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.*;
import com.jinqshen.weixin.vo.PageBean;
import com.jinqshen.weixin.vo.Transcript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jinqshen.weixin.pojo.Const;
import com.jinqshen.weixin.pojo.ExceptionMsg;
import com.jinqshen.weixin.pojo.ResponseData;
import com.jinqshen.weixin.pojo.StudentAccount;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private StudentInfoService studentInfoService;

	@Autowired
	private StudentExtraExerciseService studentExtraExerciseService;

	@Autowired
	private StudentTranscriptService studentTranscriptService;

	@Autowired
	private StudentOrderFinacoService studentOrderFinacoService;

	/**
	 * 学生登录页面接口
	 * @return
	 */
	@RequestMapping("/loginPage")
	public String loginPage(){
		return "login";
	}

	@RequestMapping("/registerPage")
	public String registerPage(){
		return "register";
	}

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
			request.getSession().setAttribute(Const.LoginUser.getValue(), studentAccount);
			return new ResponseData(ExceptionMsg.SUCCESS, "/student/entrance");
		} catch (Exception e) {
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}

	/**
	 * 注册接口
	 * @param studentAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseData register(StudentAccount studentAccount) {
		try {
			StudentInfo studentInfo = studentInfoService.findStudentInfo(studentAccount.getStudent_number());
			if(studentInfo != null){
				studentService.registStudentAccount(studentAccount.getStudent_number(), studentAccount.getStudent_password());
				return new ResponseData(ExceptionMsg.SUCCESS, "/login");
			}else{
				return new ResponseData(ExceptionMsg.NOTEXIST);
			}
		} catch (Exception e) {
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}

	/**
	 * 注销登录
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().invalidate();
		return "login";
	}

	/**
	 * 修改当前登录密码
	 * @param newpassword 新密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/newPassword")
	public String newPassword(String newpassword){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		String msg = studentService.alterPassword(studentAccount.getStudent_number(), newpassword);
		return msg;
	}

	/**
	 * 学生端  快捷入口页面接口
	 * @return
	 */
	@RequestMapping("/entrance")
	public String entrance(){
		return "entrance";
	}

	/**
	 * 学生加入课外锻炼活动接口
	 * @param extra_exercise_no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/joinExtraExercise")
	public String joinExtraExercise(int extra_exercise_no){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		String joinMsg = studentExtraExerciseService.studentJoinExercise(student_number, extra_exercise_no);
		return joinMsg;
	}

	/**
	 * 学生退出锻炼活动接口
	 * @param extra_exercise_no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exitExtraExercise")
	public String exitExtraExercise(int extra_exercise_no){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		String exitMsg = studentExtraExerciseService.studentExitExercise(student_number, extra_exercise_no);
		return exitMsg;
	}

	/**
	 * 课外锻炼活动查询接口
	 * @param page 当前页
	 * @param status 课外锻炼活动状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findExtraExercise")
	public PageBean<ExtraExercise> findExtraExercise(Integer page,String status){
		int currentPage = 1;
		if(page != null){
			currentPage = page;
		}
		PageBean<ExtraExercise> pageBean = studentExtraExerciseService.getExtraExercises(currentPage, status);
		return pageBean;
	}

	/**
	 * 学生端 课外锻炼页面接口
	 * @return
	 */
	@RequestMapping("/studentExtraExercise")
	public String findExtraExercise2(){
		return "studentExtraExercise";
	}

	/**
	 * 学生端 成绩单页面接口
	 * @return
	 */
	@RequestMapping("/transcript")
	public String transcript(){
		return "transcript";
	}

	/**
	 * 学生端 成绩单图形显示页面接口
	 * @return
	 */
	@RequestMapping("/transcriptChart")
	public String transcriptChart(){
		return "transcriptChart";
	}

	/**
	 * 查询当前学生已加入的课外锻炼序号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/joinedExtraExercises")
	public List<Integer> joinedExtraExercise(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		List<Integer> joinedExtraExercises = studentExtraExerciseService.getJoinedExtraExercise(student_number);
		return joinedExtraExercises;
	}

	/**
	 * 查询体测成绩接口
	 * @param isNewest 是否查询最新成绩
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/finacoTranscript")
	public Map<String, List<Transcript>> finacoTranscript(@RequestParam(value = "isNewest") boolean isNewest){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		Map<String, List<Transcript>> map = studentTranscriptService.getFinacoTranscript(student_number,isNewest);
		return map;
	}

	/**
	 * 体测预约首页
	 * @return
	 */
	@RequestMapping("/orderFinacoIndex")
	public String orderFinacoIndex(){
		return "orderFinacoIndex";
	}

	/**
	 * 获取所有学生的年级接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllGrade")
	public List<String> getAllGrade(){
		List<String> allGrade = studentInfoService.getAllGrade();
		return allGrade;
	}

	/**
	 * 获取所有学院接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllAcademies")
	public List<Academy> findAllAcademies(){
		List<Academy> allAcademies = studentOrderFinacoService.findAllAcademies();
		return allAcademies;
	}

	/**
	 * 获取已登录学生用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLoginStudentInfo")
	public StudentInfo getLoginStudentInfo(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		StudentInfo studentInfo = studentInfoService.findStudentInfo(student_number);
		return studentInfo;
	}

	/**
	 * 体测预约班列表
	 * @return
	 */
	@RequestMapping("/orderFinaco")
	public String OrderFinaco(){
		return "studentOrderFinaco";
	}

	/**
	 * 获取符合自身年级的体测预约班接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrderFinaco")
	public PageBean<OrderFinaco> getOrderFinaco(Integer page,String for_academy){
		int currentPage = 1;
		if(page != null){
			currentPage = page;
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		PageBean<OrderFinaco> orderFinacosPageBean = studentOrderFinacoService.getOrderFinacoByStudentNumber(currentPage,student_number,for_academy);
		return orderFinacosPageBean;
	}

	/**
	 * 查询学生已预约的体测课程号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findStudentJoinedOrderFinacoNo")
	public Integer findStudentJoinedOrderFinaco(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		Integer order_class_no = studentOrderFinacoService.findJoinedOrderFinaco(student_number);
		return order_class_no;
	}

	/**
	 * 学生预约体测接口
	 * @param order_class_no 体测课程序号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderTheFinaco")
	public String orderTheFinaco(int order_class_no){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		String msg = studentOrderFinacoService.orderTheFinaco(student_number,order_class_no);
		return msg;
	}

	/**
	 * 学生取消预约接口
	 */
	@ResponseBody
	@RequestMapping("/cancelOrder")
	public String cancelOrder(int order_class_no){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		StudentAccount studentAccount = (StudentAccount) request.getSession().getAttribute(Const.LoginUser.getValue());
		int student_number = studentAccount.getStudent_number();
		String msg = studentOrderFinacoService.cancelOrderTheFinaco(student_number,order_class_no);
		return msg;
	}

}
