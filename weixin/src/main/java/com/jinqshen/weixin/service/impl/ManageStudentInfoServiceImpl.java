package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jinqshen.weixin.mapper.ManageStudentInfoMapper;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.ManageStudentInfoService;
import com.jinqshen.weixin.utils.StudentInfoExcelUtil;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;
import com.jinqshen.weixin.vo.StudentInfoCondition;
/**
 * 学生信息管理服务层实现类
 * @author jinqshen
 *
 */
@Service
@Transactional
public class ManageStudentInfoServiceImpl implements ManageStudentInfoService {
	
	@Autowired
	private ManageStudentInfoMapper manageStudentInfoMapper;

	@Override
	public PageBean<StudentInfo> findAllStudentInfoByPage(int currentPage) {
		PageBean<StudentInfo> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		int totalSize = manageStudentInfoMapper.findCountOfAllStudentInfo();
		pageBean.setTotalSize(totalSize);
		int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
		pageBean.setTotalPage(totalPage);
		List<StudentInfo> studentinfos = manageStudentInfoMapper.findStudentInfoByPageBean(currentPage - 1, pageBean.getPageSize());
		pageBean.setInformations(studentinfos);
		return pageBean;
	}

	@Override
	public String deleteStudentInfoByStudentNumber(int student_number) {
		String deleteMsg = "删除成功";
		try {
			manageStudentInfoMapper.deleteStudentInfoByStudentNumber(student_number);
		} catch (Exception e) {
			deleteMsg = "删除失败";
		}
		return deleteMsg;
	}

	@Override
	public StudentInfo editStudentInfoByStudentNumber(int student_number) {
		StudentInfo studentInfo = manageStudentInfoMapper.findStudentInfoByStudentNumber(student_number);
		return studentInfo;
	}

	@Override
	public String updateOneStudentInfo(StudentInfo studentInfo) {
		String updateMsg = "更新成功";
		try {
			manageStudentInfoMapper.updateOneStudentInfoByEntity(studentInfo);
		} catch (Exception e) {
			updateMsg = "更新失败";
		}
		return updateMsg;
	}

	@Override
	public String addOneStudentInfo(StudentInfo studentInfo) {
		String insertMsg = "新增成功";
		StudentInfo student = manageStudentInfoMapper.findStudentInfoByStudentNumber(studentInfo.getStudent_number());
		if(student != null) {
			insertMsg = "该学生信息已存在";
			return insertMsg;
		}
		try {
			manageStudentInfoMapper.insertStudentInfo(studentInfo);
		} catch (Exception e) {
			insertMsg = "新增失败";
		}
		return insertMsg;
	}

	@Override
	public PageBean<StudentInfo> findStudentInfoByCondition(int currentPage, int student_number, String name,
			String academy) {
		PageBean<StudentInfo> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		int totalSize = manageStudentInfoMapper.findCountOfStudentInfoBySomeCondition(student_number,name,academy);
		pageBean.setTotalSize(totalSize);
		int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
		pageBean.setTotalPage(totalPage);
		List<StudentInfo> studentInfos = manageStudentInfoMapper.findStudentInfoBySomeCondition((currentPage - 1)*pageBean.getPageSize(), pageBean.getPageSize(),student_number,name,academy);
		pageBean.setInformations(studentInfos);
		return pageBean;
	}
	
	@Override
	public PageBean<StudentInfo> searchStudentInfoByHighCondition(int currentPage, List<HighCondition> highConditions) {
		PageBean<StudentInfo> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		int totalSize = manageStudentInfoMapper.findCountOfStudentInfoByHighCondition(highConditions);
		pageBean.setTotalSize(totalSize);
		int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
		pageBean.setTotalPage(totalPage);
		List<StudentInfo> studentInfos = manageStudentInfoMapper.findStudentInfoByHighCondition((currentPage - 1)*pageBean.getPageSize(), pageBean.getPageSize(), highConditions);
		pageBean.setInformations(studentInfos);
		return pageBean;
	}
	
	@Override
	public String saveStudentInfoExcel(MultipartFile multipartFile) {
		StudentInfoExcelUtil studentInfoExcelUtil = new StudentInfoExcelUtil();
		int insertnum = 0;
		String insertmsg = "";
		List<StudentInfo> list = studentInfoExcelUtil.getExcelInfo(multipartFile);
		for (StudentInfo studentInfo : list) {
			insertnum += manageStudentInfoMapper.insertStudentInfo(studentInfo);
		}
		if(insertnum != studentInfoExcelUtil.getTotalRows() - 1) {
			insertmsg = "数据保存失败";
		}
		else {
			insertmsg = "数据保存成功";
		}
		return insertmsg;
	}

	

}
