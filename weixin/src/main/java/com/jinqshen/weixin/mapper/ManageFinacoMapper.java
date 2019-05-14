package com.jinqshen.weixin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.table.Finaco;
import com.jinqshen.weixin.vo.HighCondition;

@Repository
public interface ManageFinacoMapper {

	/**
	 * 查询符合条件的数目
	 * @param highConditions 条件
	 * @return
	 */
 	public int findCountOfFinacoTestResultByHighCondition(List<HighCondition> highConditions);

 	/**
 	 * 查询符合条件的体测结果
 	 * @param currentPage 当前页起始序号
 	 * @param pageSize 页面显示条数
 	 * @param highConditions 条件
 	 * @return
 	 */
	public List<Finaco> findFinacoTestResultByHighCondition(@Param("currentPage")int currentPage, @Param("pageSize")int pageSize, @Param("list")List<HighCondition> highConditions);

	/**
	 * 查询所有的学期
	 * @return
	 */
	public List<String> findAllSemester();
	
	/**
	 * 查询所有的年级
	 * @return
	 */
	public List<String> findAllGrade();

	/**
	 * 插入一条体测结果信息
	 * @param finaco 体测结果对象
	 */
	public void insertOneFinacoTestResult(Finaco finaco);

	/**
	 * 更新一条存在的体测结果信息
	 * @param finaco 体测结果对象
	 */
	public void updateOneFinacoTestResult(Finaco finaco);

	/**
	 * 通过finaco_no查询体测结果
	 * @param finaco_no
	 * @return
	 */
	public Finaco findFinacoTestResultByFinaco_no(int finaco_no);

	/**
	 * 通过finaco_no删除体测结果
	 * @param finaco_no
	 */
	public void deleteFinacoTestResultByFinaco_no(int finaco_no);

}
