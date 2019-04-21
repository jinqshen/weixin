package com.jinqshen.weixin.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.table.Major;

@Repository
public interface ManageMajorMapper {

	/**
	 * 查询所有的专业
	 * @return
	 */
	public List<Major> findAllMajor();
}
