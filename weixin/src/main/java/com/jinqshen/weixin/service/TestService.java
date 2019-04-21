package com.jinqshen.weixin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;

public interface TestService {

	public String saveExcelDataService(MultipartFile mFile);

	public PageBean<StudentInfo> searchStudentInfoList(int currenPage,List<HighCondition> highConditions);

}
