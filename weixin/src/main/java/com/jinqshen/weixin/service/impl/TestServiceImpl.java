package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinqshen.weixin.mapper.ManageStudentInfoMapper;
import com.jinqshen.weixin.mapper.TestMapper;
import com.jinqshen.weixin.pojo.entity.Players;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.TestService;
import com.jinqshen.weixin.utils.ExcelUtil;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private ManageStudentInfoMapper manageStudentInfoMapper;
	
	@Override
	public String saveExcelDataService(MultipartFile mFile) {
		ExcelUtil excelUtil = new ExcelUtil();
		int insertnum = 0;
		String insertmsg = "";
		List<Players> list = excelUtil.getExcelInfo(mFile);
		for (Players players : list) {
			insertnum += testMapper.insertPlayer(players);
		}
		if(insertnum != excelUtil.getTotalRows() - 1) {
			insertmsg = "数据保存失败";
		}
		else {
			insertmsg = "数据保存成功";
		}
		return insertmsg;
	}

	@Override
	public PageBean<StudentInfo> searchStudentInfoList(int currentPage,List<HighCondition> highConditions) {
		PageBean<StudentInfo> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		int totalSize = 0;
		int condition = manageStudentInfoMapper.findCountOfStudentInfoByHighCondition(highConditions);
		return pageBean;
	}


}
