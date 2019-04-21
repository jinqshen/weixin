package com.jinqshen.weixin.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.table.Academy;

@Repository
public interface ManageAcademyMapper {

	/**
	 * 查询所有的学院信息
	 * @return
	 */
	public List<Academy> findAllAcademyInfo();
}
