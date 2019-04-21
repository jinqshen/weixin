package com.jinqshen.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.ManageStudentInfoService;
import com.jinqshen.weixin.service.TestService;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;

/**
 * 接口测试控制层
 * @author jinqshen
 *
 */
@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private TestService testService;
	
	@Autowired
	private ManageStudentInfoService manageStudentInfoService;
	
	@RequestMapping("/importData")
	public String importData() {
		return "import";
	}
	
	@RequestMapping("/highSearch")
	public String highSearch() {
		return "HighSearch";
	}
	
	@RequestMapping("/sss")
	public ModelAndView sss(String conditions) {
		ModelAndView mav = new ModelAndView();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(conditions).getAsJsonArray();
		List<HighCondition> highConditions = new ArrayList<>();
		for (JsonElement highCondition : jsonArray) {
			Gson gson = new Gson();
			HighCondition condition = gson.fromJson(highCondition, HighCondition.class);
			highConditions.add(condition);
		}
		int currentPage = 1;
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
		PageBean<StudentInfo> pageBean = null;
		pageBean = manageStudentInfoService.searchStudentInfoByHighCondition(currentPage, highConditions);
		mav.addObject("studentInfoPageBean", pageBean);
		mav.setViewName("studentInfo");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/saveExcelData")
	public String saveExcelData(@RequestParam(value="mfile") MultipartFile mFile) {
		String msg = testService.saveExcelDataService(mFile);
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/saveMoreExcelData")
	public String saveMoreExcelData(@RequestParam(value="mfile") MultipartFile[] mFile) {
		for (MultipartFile multipartFile : mFile) {
			testService.saveExcelDataService(multipartFile);
		}
		return "{}";
	}
}
