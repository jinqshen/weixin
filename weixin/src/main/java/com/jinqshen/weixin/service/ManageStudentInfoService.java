package com.jinqshen.weixin.service;
/**
 * 学生信息管理服务层
 * @author jinqshen
 *
 */

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;
import com.jinqshen.weixin.vo.StudentInfoCondition;

public interface ManageStudentInfoService {
	
	/**
	 * 查询分页对象pageBean
	 * @param currentPage 当前页
	 * @return
	 */
	public PageBean<StudentInfo> findAllStudentInfoByPage(int currentPage);

	/**
	 * 通过学号删除学生信息
	 * @param student_number 学号
	 * @return
	 */
	public String deleteStudentInfoByStudentNumber(int student_number);

	/**
	 * 编辑学生信息
	 * @param student_number 学号
	 * @return
	 */
	public StudentInfo editStudentInfoByStudentNumber(int student_number);

	/**
	 * 更新学生信息
	 * @param studentInfo 学生信息对象
	 * @return
	 */
	public String updateOneStudentInfo(StudentInfo studentInfo);

	/**
	 * 增加一位学生信息
	 * @param studentInfo 学生信息对象
	 * @return
	 */
	public String addOneStudentInfo(StudentInfo studentInfo);

	/**
	 * 根据条件查询学生信息
	 * @param currentPage 当前页
	 * @param student_number 学号
	 * @param name 姓名
	 * @param academy 所在学院
	 * @return
	 */
	public PageBean<StudentInfo> findStudentInfoByCondition(int currentPage, int student_number, String name, String academy);

	/**
	 * 根据条件列表List查询学生信息
	 * @param currentPage 当前页
	 * @param highConditions 条件列表
	 * @return
	 */
	public PageBean<StudentInfo> searchStudentInfoByHighCondition(int currentPage, List<HighCondition> highConditions);
	
	/**
	 * 保存单个学生信息Excel表的数据
	 * @param multipartFile 学生信息Excel表
	 * @return
	 */
	public String saveStudentInfoExcel(MultipartFile multipartFile);
}
