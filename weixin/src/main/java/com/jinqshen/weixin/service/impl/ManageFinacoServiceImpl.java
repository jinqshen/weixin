package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinqshen.weixin.mapper.ManageFinacoMapper;
import com.jinqshen.weixin.pojo.table.Finaco;
import com.jinqshen.weixin.service.ManageFinacoService;
import com.jinqshen.weixin.utils.FinacoExcelUtil;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;
@Service
public class ManageFinacoServiceImpl implements ManageFinacoService {

	@Autowired
	private ManageFinacoMapper manageFinacoMapper;
	
	@Override
	public PageBean<Finaco> searchFinacoTestResultByHighCondition(int currentPage, List<HighCondition> highConditions) {
		PageBean<Finaco> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		int totalSize = manageFinacoMapper.findCountOfFinacoTestResultByHighCondition(highConditions);
		pageBean.setTotalSize(totalSize);
		int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
		pageBean.setTotalPage(totalPage);
		List<Finaco> finacos = manageFinacoMapper.findFinacoTestResultByHighCondition((currentPage - 1)*pageBean.getPageSize(),pageBean.getPageSize(),highConditions);
		pageBean.setInformations(finacos);
		return pageBean;
	}

	@Override
	public List<String> getAllSemester() {
		return manageFinacoMapper.findAllSemester();
	}

	@Override
	public List<String> getAllGrade() {
		return manageFinacoMapper.findAllGrade();
	}

	@Override
	public String addfinacoTestResult(Finaco finaco) {
		String msg = "添加成功";
		Integer project_no = finaco.getProject_no();
		float test_result = 0.0f;
		try {
			//若体测项目为1000米或者800米
			if(project_no.equals(100010) || project_no.equals(100070)) {
				String test_result_describe = finaco.getTest_result_describe();
				String[] ms = test_result_describe.split("\'");
				if(ms == null || ms.length != 2)
					throw new Exception();
				else {
					test_result = Integer.parseInt(ms[0])*60 + Integer.parseInt(ms[1]);
					finaco.setTest_result(test_result);
				}
			}
			//若体测项目为50米
			else if (project_no.equals(100020)) {
				String test_result_describe = finaco.getTest_result_describe();
				String[] ms = test_result_describe.split("\"");
				if(ms == null || ms.length != 2)
					throw new Exception();
				else {
					test_result = Integer.parseInt(ms[0]) + Integer.parseInt(ms[1])*0.1f;
					finaco.setTest_result(test_result);
				}
			}
			//若体测项目为其他项目
			else {
				finaco.setTest_result(Integer.parseInt(finaco.getTest_result_describe()));
			}
			manageFinacoMapper.insertOneFinacoTestResult(finaco);
		} catch (Exception e) {
			msg = "添加失败";
		}
		return msg;
	}

	@Override
	public String saveFinacoTestResult(Finaco finaco) {
		String msg = "保存成功";
		Integer project_no = finaco.getProject_no();
		float test_result = 0.0f;
		try {
			//若体测项目为1000米或者800米
			if(project_no.equals(100010) || project_no.equals(100070)) {
				String test_result_describe = finaco.getTest_result_describe();
				String[] ms = test_result_describe.split("\'");
				if(ms == null || ms.length != 2)
					throw new Exception();
				else {
					test_result = Integer.parseInt(ms[0])*60 + Integer.parseInt(ms[1]);
					finaco.setTest_result(test_result);
				}
			}
			//若体测项目为50米
			else if (project_no.equals(100020)) {
				String test_result_describe = finaco.getTest_result_describe();
				String[] ms = test_result_describe.split("\"");
				if(ms == null || ms.length != 2)
					throw new Exception();
				else {
					test_result = Integer.parseInt(ms[0]) + Integer.parseInt(ms[1])*0.1f;
					finaco.setTest_result(test_result);
				}
			}
			//若体测项目为其他项目
			else {
				finaco.setTest_result(Integer.parseInt(finaco.getTest_result_describe()));
			}
			manageFinacoMapper.updateOneFinacoTestResult(finaco);
		} catch (Exception e) {
			msg = "保存失败";
		}
		return msg;
	}

	@Override
	public Finaco searchFinacoTestResultByFinaco_no(int finaco_no) {
		return manageFinacoMapper.findFinacoTestResultByFinaco_no(finaco_no);
	}

	@Override
	public String removeFinacoTestResult(int finaco_no) {
		String msg = "删除成功";
		try {
			manageFinacoMapper.deleteFinacoTestResultByFinaco_no(finaco_no);
		} catch (Exception e) {
			msg = "删除失败";
		}
		return msg;
	}

	@Override
	public void saveFinacoResultExcel(MultipartFile multipartFile) {
		FinacoExcelUtil finacoExcelUtil = new FinacoExcelUtil();
		List<Finaco> excelInfoList = finacoExcelUtil.getExcelInfo(multipartFile);
		for (Finaco finaco : excelInfoList) {
			addfinacoTestResult(finaco);
		}
	}

}
