package com.jinqshen.weixin.service;
/**
 * 后台管理专业服务层
 * @author jinqshen
 *
 */

import java.util.List;
import com.jinqshen.weixin.pojo.table.Major;

public interface ManageMajorService {

	/**
	 * 查询所有的专业
	 * @return
	 */
	public List<Major> findAllMajors();
}
