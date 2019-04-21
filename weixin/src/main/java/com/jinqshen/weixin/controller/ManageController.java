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
import com.jinqshen.weixin.pojo.table.Academy;
import com.jinqshen.weixin.pojo.table.Major;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.ManageAcademyService;
import com.jinqshen.weixin.service.ManageMajorService;
import com.jinqshen.weixin.service.ManageStudentInfoService;
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
	
	/**
	 * 后台查询符合条件所有学生信息接口
	 * @return
	 */
	@RequestMapping("/studentInfoList")
	public ModelAndView studentInfoList() {
		ModelAndView mav = new ModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String page = request.getParameter("page");
		String snumber = request.getParameter("student_number");
		String sname = request.getParameter("name");
		String sacademy = request.getParameter("academy");
		int currentPage = 1;
		String student_number = "";
		String name = "";
		String academy = "";
		PageBean<StudentInfo> pageBean = null;
		if(page != null)
			currentPage = Integer.parseInt(page);
		if(snumber !=null && !snumber.trim().equals(""))
			student_number = snumber;
		if(sname != null && !sname.trim().equals(""))
			name = sname;
		if(sacademy != null && !sacademy.trim().equals(""))
			academy = sacademy;
		StudentInfoCondition condition = new StudentInfoCondition(student_number, name, academy);
		try {
			Integer.parseInt(snumber);
		} catch (Exception e) {
			student_number = "0000000";
		}
		pageBean = manageStudentInfoService.findStudentInfoByCondition(currentPage,Integer.parseInt(student_number),name,academy);
		List<Academy> academies = manageAcademyService.findAllAcademy();
		List<Major> majors = manageMajorService.findAllMajors();
		mav.addObject("studentInfoCondition", condition);
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
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
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
	
	@RequestMapping("/studentInfoList1")
	public ModelAndView studentInfoList1(String conditions) {
		ModelAndView mav = new ModelAndView();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String page = request.getParameter("page");
		PageBean<StudentInfo> pageBean = null;
		List<HighCondition> highConditions = new ArrayList<>();
		int currentPage = 1;
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
			HighCondition highCondition = new HighCondition();
			highCondition.setConditionValue("");
			highCondition.setConditionName("name");
			highCondition.setAndor("");
			highConditions.add(highCondition);
		}
		pageBean = manageStudentInfoService.searchStudentInfoByHighCondition(currentPage, highConditions);
		StudentInfoCondition condition = new StudentInfoCondition("", "", "");
		List<Academy> academies = manageAcademyService.findAllAcademy();
		List<Major> majors = manageMajorService.findAllMajors();
		mav.addObject("studentInfoCondition", condition);
		mav.addObject("studentInfoPageBean", pageBean);
		mav.addObject("academies", academies);
		mav.addObject("majors", majors);
		mav.setViewName("studentInfo");
		return mav;
	}
}
