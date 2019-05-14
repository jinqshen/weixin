package com.jinqshen.weixin.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.table.FinacoProject;
/**
 * 体测项目持久层
 * @author jinqshen
 *
 */
@Repository
public interface ManageFinacoProjectMapper {

	/**
	 * 查询所有的体侧项目
	 * @return
	 */
	public List<FinacoProject> findAllFinacoProject();

}
