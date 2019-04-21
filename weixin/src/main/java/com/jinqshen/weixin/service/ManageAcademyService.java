package com.jinqshen.weixin.service;
/**
 * 后台管理学院信息服务层
 * @author jinqshen
 *
 */

import java.util.List;
import com.jinqshen.weixin.pojo.table.Academy;

public interface ManageAcademyService {

	/**
	 * 查询所有学院信息
	 * @return
	 */
	public List<Academy> findAllAcademy();
}
