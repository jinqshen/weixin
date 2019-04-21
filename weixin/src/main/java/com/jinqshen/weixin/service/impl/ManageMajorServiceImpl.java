package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinqshen.weixin.mapper.ManageMajorMapper;
import com.jinqshen.weixin.pojo.table.Major;
import com.jinqshen.weixin.service.ManageMajorService;
@Service
@Transactional
public class ManageMajorServiceImpl implements ManageMajorService {

	@Autowired
	private ManageMajorMapper manageMajorMapper;
	
	@Override
	public List<Major> findAllMajors() {
		List<Major> majors = manageMajorMapper.findAllMajor();
		return majors;
	}

}
