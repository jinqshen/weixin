package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinqshen.weixin.mapper.ManageFinacoProjectMapper;
import com.jinqshen.weixin.pojo.table.FinacoProject;
import com.jinqshen.weixin.service.ManageFinacoProjectService;
@Service
public class ManageFinacoProjectServiceImpl implements ManageFinacoProjectService {

	@Autowired
	private ManageFinacoProjectMapper manageFinacoProjectMapper;
	
	@Override
	public List<FinacoProject> getAllFinacoProject() {
		return manageFinacoProjectMapper.findAllFinacoProject();
	}

}
