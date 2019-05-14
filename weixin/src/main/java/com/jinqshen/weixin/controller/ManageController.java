package com.jinqshen.weixin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jinqshen.weixin.pojo.Const;
import com.jinqshen.weixin.pojo.ExceptionMsg;
import com.jinqshen.weixin.pojo.ResponseData;
import com.jinqshen.weixin.pojo.table.*;
import com.jinqshen.weixin.service.*;
import com.jinqshen.weixin.utils.StudentInfoExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;
import com.jinqshen.weixin.vo.StudentInfoCondition;

/**
 * 后台信息管理控制层
 * @author jinqshen
 *
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

	@Autowired
	private ManageStudentInfoService manageStudentInfoService;
	
	@Autowired
	private ManageAcademyService manageAcademyService;
	
	@Autowired
	private ManageMajorService manageMajorService;
	
	@Autowired
	private ManageFinacoService manageFinacoService;
	
	@Autowired
	private ManageFinacoProjectService manageFinacoProjectService;

	@Autowired
	private ManageExtraExerciseService manageExtraExerciseService;

	@Autowired
	private ManageOrderFinacoService manageOrderFinacoService;

	@Autowired
	private ManageService manageService;

	/**
	 * 后台登录页面接口
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "manageLogin";
	}

	/**
	 * 后台登录账户验证接口
	 * @param manageAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loginValidate")
	public ResponseData loginValidate(ManageAccount manageAccount){
		try{
			ManageAccount loginManageAccount = manageService.findManageAccount(manageAccount);
			if(loginManageAccount == null){
				return new ResponseData(ExceptionMsg.ManageLoginameOrPassWordError);
			}
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = servletRequestAttributes.getRequest();
			request.getSession().setAttribute(Const.LoginManageUser.getValue(),loginManageAccount);
			return new ResponseData(ExceptionMsg.SUCCESS,"/manage/orderFinacoView");
		}catch (Exception e){
			return new ResponseData(ExceptionMsg.FAILED);
		}
	}

	/**
	 * 获取已登录管理账户用户名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserName")
	public String getUserName(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		ManageAccount manageAccount = (ManageAccount) request.getSession().getAttribute(Const.LoginManageUser.getValue());
		return manageAccount.getManage_username();
	}

	/**
	 * 注销接口
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		request.getSession().invalidate();
		return "manageLogin";
	}

	/**
	 * 修改管理员密码接口
	 * @param newpassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/newPassword")
	public String newPassword(String newpassword){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		ManageAccount manageAccount = (ManageAccount) request.getSession().getAttribute(Const.LoginManageUser.getValue());
		String msg = manageService.updatePassword(manageAccount.getManage_username(), newpassword);
		return msg;
	}

	/**
	 * 新增管理账户
	 * @param manageAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addManageAccount")
	public String addManageAccount(ManageAccount manageAccount){
		String msg = manageService.addManageAccount(manageAccount);
		return msg;
	}

	/**
	 * 后台查询符合条件所有学生信息接口
	 * @return
	 */
	@RequestMapping("/studentInfoList")
	public ModelAndView studentInfoList() {
		ModelAndView mav = new ModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String page = request.getParameter("page");
		String conditions = request.getParameter("conditions");
		PageBean<StudentInfo> pageBean = null;
		List<HighCondition> highConditions = new ArrayList<>();
		int currentPage = 1;
		if(page != null && !page.equals("")) {
			currentPage = Integer.parseInt(page);
		}
		if(conditions != null) {
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = jsonParser.parse(conditions).getAsJsonArray();
			for (JsonElement highCondition : jsonArray) {
				Gson gson = new Gson();
				HighCondition condition = gson.fromJson(highCondition, HighCondition.class);
				highConditions.add(condition);
			}
			
			for (HighCondition highCondition : highConditions) {
				if(highCondition.getConditionName().equals("学号"))
					highCondition.setConditionName("student_number");
				if(highCondition.getConditionName().equals("姓名"))
					highCondition.setConditionName("name");
				if(highCondition.getConditionName().equals("性别"))
					highCondition.setConditionName("sex");
				if(highCondition.getConditionName().equals("所在学院"))
					highCondition.setConditionName("academy");
				if(highCondition.getConditionName().equals("专业"))
					highCondition.setConditionName("major");
				if(highCondition.getConditionName().equals("身份证号"))
					highCondition.setConditionName("id_number");
			}
		}else {
			HighCondition highCondition = new HighCondition("student_number","","");
			highConditions.add(highCondition);
		}
		pageBean = manageStudentInfoService.searchStudentInfoByHighCondition(currentPage, highConditions);
		List<Academy> academies = manageAcademyService.findAllAcademy();
		List<Major> majors = manageMajorService.findAllMajors();
		mav.addObject("studentInfoHighConditions", highConditions);
		mav.addObject("studentInfoPageBean", pageBean);
		mav.addObject("academies", academies);
		mav.addObject("majors", majors);
		mav.setViewName("studentInfo");
		return mav;
	}
	
	/**
	 * 后台删除学生信息接口
	 * @param student_number 学号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteStudentInfo")
	public String deleteStudentInfo(int student_number) {
		String msg = manageStudentInfoService.deleteStudentInfoByStudentNumber(student_number);
		return msg;
	}
	
	/**
	 * 后台编辑学生信息接口
	 * @param student_number 学号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editStudentInfo")
	public StudentInfo editStudentInfo(int student_number) {
		StudentInfo studentInfo = manageStudentInfoService.editStudentInfoByStudentNumber(student_number);
		return studentInfo;
	}
	
	/**
	 * 后台更新学生信息接口
	 * @param studentInfo 学生信息对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateStudentInfo")
	public String updateStudentInfo(StudentInfo studentInfo) {
		String msg = manageStudentInfoService.updateOneStudentInfo(studentInfo);
		return msg;
	}
	
	/**
	 * 后台通过导入Excel导入学生数据
	 * @param multipartFiles 一些excel文件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/importStudentInfoExcel")
	public String importStudentInfoExcel(@RequestParam(value="mfile") MultipartFile[] multipartFiles) {
		if(multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				manageStudentInfoService.saveStudentInfoExcel(multipartFile);
			}
		}
		return "数据保存成功";
	}
	
	/**
	 * 后台新增学生信息接口
	 * @param studentInfo 学生信息对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addStudentInfo")
	public String addStudentInfo(StudentInfo studentInfo) {
		String msg = manageStudentInfoService.addOneStudentInfo(studentInfo);
		return msg;
	}
	
	/**
	 * 学生信息样例表下载
	 * @param request http请求
	 * @param response http回应
	 */
	@RequestMapping("/downloadStudentInfoExcel")
	public void downloadStudentInfoExcel(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "学生信息Excel样例表.xlsx";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("C:\\Users\\jinqshen\\Desktop\\学生信息Excel样例表.xlsx");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                try {
					response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
				} catch (UnsupportedEncodingException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
	}
	
	/**
	 * 后台查询符合条件所有体测结果信息接口
	 * @return
	 */
	@RequestMapping("/finacoTestResult")
	public ModelAndView finacoTestResult() {
		ModelAndView mav = new ModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String page = request.getParameter("page");
		String conditions = request.getParameter("conditions");
		List<HighCondition> highConditions = new ArrayList<>();
		PageBean<Finaco> pageBean = null;
		int currentPage = 1;
		if(page != null && page != "") {
			currentPage = Integer.parseInt(page);
		}
		if(conditions != null) {
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = jsonParser.parse(conditions).getAsJsonArray();
			Gson gson = new Gson();
			for (JsonElement jsonElement : jsonArray) {
				HighCondition highCondition = gson.fromJson(jsonElement, HighCondition.class);
				highConditions.add(highCondition);
			}
			for (HighCondition highCondition : highConditions) {
				if(highCondition.getConditionName().equals("学号"))
					highCondition.setConditionName("student_number");
				if(highCondition.getConditionName().equals("体测项目"))
					highCondition.setConditionName("project_no");
				if(highCondition.getConditionName().equals("学期"))
					highCondition.setConditionName("semester");
				if(highCondition.getConditionName().equals("年级"))
					highCondition.setConditionName("grade");
			}
		}
		else {
			HighCondition highCondition = new HighCondition("student_number","","");
			highConditions.add(highCondition);
		}
		pageBean = manageFinacoService.searchFinacoTestResultByHighCondition(currentPage, highConditions);
		List<String> semesters = manageFinacoService.getAllSemester();
		List<String> grades = manageFinacoService.getAllGrade();
		List<FinacoProject> finacoProjects = manageFinacoProjectService.getAllFinacoProject();
		mav.addObject("finacoPageBean", pageBean);
		mav.addObject("finacoProjects", finacoProjects);
		mav.addObject("semesters",semesters);
		mav.addObject("grades",grades);
		mav.addObject("finacoHighConditions", highConditions);
		mav.setViewName("finacoTestResult");
		return mav;
	}
	
	/**
	 * 编辑体测结果记录接口，通过finaco_no得到当前记录信息
	 * @param finaco_no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editFinacoTestResult")
	public Finaco editFinacoTestResult(int finaco_no) {
		Finaco finaco = manageFinacoService.searchFinacoTestResultByFinaco_no(finaco_no);
		return finaco;
	}
	
	/**
	 * 新增一条体测结果记录接口
	 * @param finaco
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertFinacoTestResult")
	public String addOnefinacoTestResult(Finaco finaco) {
		String msg = manageFinacoService.addfinacoTestResult(finaco);
		return msg;
	}
	
	/**
	 * 体测成绩Excel表导入接口
	 * @param multipartFiles
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/importFinacoResultExcel")
	public String importFinacoResultExcel(@RequestParam(value="mfile") MultipartFile[] multipartFiles) {
		if(multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				manageFinacoService.saveFinacoResultExcel(multipartFile);
			}
		}
		return "数据保存成功";
	}
	
	/**
	 * 更新一条体测结果记录接口
	 * @param finaco
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateFinacoTestResult")
	public String updateFinacoTestResult(Finaco finaco) {
		String msg = manageFinacoService.saveFinacoTestResult(finaco);
		return msg;
	}
	
	/**
	 * 删除体测结果记录接口
	 * @param finaco_no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteFinacoTestResult")
	public String deleteFinacoTestResult(int finaco_no) {
		String msg = manageFinacoService.removeFinacoTestResult(finaco_no);
		return msg;
	}
	
	/**
	 * 下载体测结果样例Excel表接口
	 * @param request http请求
	 * @param response http响应
	 */
	@RequestMapping("/downloadFinacoTestResultExcel")
	public void downloadFinacoTestResultExcel(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "体测结果录入Excel样例表.xlsx";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("C:\\Users\\jinqshen\\Desktop\\体测结果录入Excel样例表.xlsx");
            //File file = new File(realPath , fileName);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				try {
					response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
				} catch (UnsupportedEncodingException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * 后台课外锻炼活动管理接口
	 * @return
	 */
	@RequestMapping("/extraExercise")
	public String getViewExtraExercise(){
		return "extraExercise";
	}

	@ResponseBody
	@RequestMapping("/searchExtraExercise")
	public PageBean<ExtraExercise> searchExtraExercise(Integer page, String status, String teacher){
		int currentPage = 1;
		if(page != null){
			currentPage = page;
		}
		PageBean<ExtraExercise> pageBean = manageExtraExerciseService.findExtraExercises(currentPage, status, teacher);
		return pageBean;
	}

	/**
	 * 删除课外锻炼接口
	 * @param extra_exercise_no 课外锻炼序号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTheExtraExercise")
	public String deleteTheExtraExercise(int extra_exercise_no){
		String msg = manageExtraExerciseService.removeTheExtraExercise(extra_exercise_no);
		return msg;
	}

	/**
	 * 新增课外锻炼接口
	 * @param extraExercise
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/newExtraExercise")
	public String newExtraExercise(ExtraExercise extraExercise){
		String msg = manageExtraExerciseService.insertOneExtraExercise(extraExercise);
		return msg;
	}

	/**
	 * 编辑课外锻炼活动接口
	 * @param extra_exercise_no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editExtraExercise")
	public ExtraExercise editExtraExercise(int extra_exercise_no){
		ExtraExercise extraExercise = manageExtraExerciseService.findExtraExercise(extra_exercise_no);
		return extraExercise;
	}

	/**
	 * 更新课外锻炼接口
	 * @param extraExercise
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateExtraExercise")
	public String updateExtraExercise(ExtraExercise extraExercise){
		String msg = manageExtraExerciseService.updateExtraExercise(extraExercise);
		return msg;
	}

	/**
	 * 查询参与该课外锻炼人数接口
	 * @param extra_exercise_no 课外锻炼序号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findCountOfStudent")
	public Integer findCountOfStudent(int extra_exercise_no){
		int currentNumberOfStudent = manageExtraExerciseService.findJoinedCountOfStudent(extra_exercise_no);
		return currentNumberOfStudent;
	}

	/**
	 * 查询加入该课外的学生接口
	 * @param page 当前页码
	 * @param extra_exercise_no 课外锻炼序号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findJoinedStudentInfos")
	public PageBean<StudentInfo> findJoinedStudentInfos(Integer page,int extra_exercise_no){
		int currentPage = 1;
		if(page != null){
			currentPage = page;
		}
		PageBean<StudentInfo> pageBean = manageExtraExerciseService.findJoinedStudentInfos(currentPage, extra_exercise_no);
		return pageBean;
	}

	/**
	 * 导出学生信息接口
	 * @param request http请求
	 * @param response http响应
	 * @param extra_exercise_no 课外锻炼活动序号
	 */
	@RequestMapping("/exportJoinedStudentInfos")
    public void exportJoinedStudentInfos(HttpServletRequest request,HttpServletResponse response,@RequestParam("extra_exercise_no") int extra_exercise_no){
	    List<StudentInfo> studentInfos = manageExtraExerciseService.findAllJoinedStudentInfos(extra_exercise_no);
        StudentInfoExcelUtil studentInfoExcelUtil = new StudentInfoExcelUtil();
        String[] headers = {"学号","姓名","性别","专业","所在学院","出生年月","身份证号"};
        String fileName = "学生信息";
        studentInfoExcelUtil.exportExcel(headers,studentInfos,fileName,response);
    }

    @RequestMapping("/orderFinacoView")
	public String orderFinacoView(){
		return "orderFinacoView";
	}

	/**
	 * 查询符合条件的体测预约班接口
	 * @param page 当前页
	 * @param for_grade 年级
	 * @param for_academy 学院
	 * @return
	 */
    @ResponseBody
    @RequestMapping("/orderFinaco")
	public PageBean<OrderFinaco> orderFinaco(Integer page, @RequestParam(value = "for_grade") String for_grade, @RequestParam(value = "for_academy") String for_academy){
		int currentPage = 1;
		if(page != null){
			currentPage = page;
		}
		PageBean<OrderFinaco> pageBean = manageOrderFinacoService.getOrderFinacos(currentPage, for_grade, for_academy);
		return pageBean;
	}

	/**
	 * 删除体测预约班接口
	 * @param order_class_no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteOrderFinaco")
	public String deleteOrderFinaco(int order_class_no){
    	String msg = manageOrderFinacoService.deleteOrderFinaco(order_class_no);
    	return msg;
	}

	/**
	 * 编辑体测预约班信息
	 * @param order_class_no 体测预约班号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editOrderFinaco")
	public OrderFinaco editOrderFinaco(int order_class_no){
		OrderFinaco orderFinaco = manageOrderFinacoService.getOrderFinaco(order_class_no);
		return orderFinaco;
	}

	/**
	 * 更新体测预约班信息
	 * @param orderFinaco
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateOrderFinaco")
	public String updateOrderFinaco(OrderFinaco orderFinaco){
		String msg = manageOrderFinacoService.updateOrderFinaco(orderFinaco);
		return msg;
	}

	/**
	 * 新增体测预约班接口
	 * @param orderFinaco 体测预约班对象
	 * @param for_academies 针对学院
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/newOrderFinacoClass")
	public String newOrderFinacoClass(OrderFinaco orderFinaco,@RequestParam(value = "for_academies[]") String[] for_academies){
		String msg = "";
		if(for_academies.length > 0){
			for (String for_academy : for_academies) {
				orderFinaco.setFor_academy(for_academy);
				String msginfo = manageOrderFinacoService.newOrderFinacoClass(orderFinaco);
				msg = msg + msginfo + ";";
			}
		}
		return msg;
	}

	/**
	 * 获取所有的学院接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllAcademies")
	public List<Academy> findAllAcademies(){
		return manageAcademyService.findAllAcademy();
	}

}
