package com.jinqshen.weixin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jinqshen.weixin.pojo.table.Finaco;
import com.jinqshen.weixin.vo.HighCondition;
import com.jinqshen.weixin.vo.PageBean;

public interface ManageFinacoService {

	/**
	 * 根据动态条件查询学生体测结果
	 * @param currentPage 当前页
	 * @param highConditions 搜索条件
	 * @return
	 */
	public PageBean<Finaco> searchFinacoTestResultByHighCondition(int currentPage,List<HighCondition> highConditions);

	/**
	 * 查询所有的学期
	 * @return
	 */
	public List<String> getAllSemester();

	/**
	 * 查询所有的年级
	 * @return
	 */
	public List<String> getAllGrade();

	/**
	 * 增加体测测试结果
	 * @param finaco
	 * @return
	 */
	public String addfinacoTestResult(Finaco finaco);

	/**
	 * 保存修改后的体测结果
	 * @param finaco
	 * @return
	 */
	public String saveFinacoTestResult(Finaco finaco);

	/**
	 * 通过finaco_no查询体测结果
	 * @param finaco_no 体测结果序号
	 * @return
	 */
	public Finaco searchFinacoTestResultByFinaco_no(int finaco_no);

	/**
	 * 删除一条体测结果
	 * @param finaco_no 体测结果序号
	 * @return
	 */
	public String removeFinacoTestResult(int finaco_no);

	/**
	 * 读取excel文件，将体测结果数据存入库中
	 * @param multipartFile
	 */
	public void saveFinacoResultExcel(MultipartFile multipartFile);
}
