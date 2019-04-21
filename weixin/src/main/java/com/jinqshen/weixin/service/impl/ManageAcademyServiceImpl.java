package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinqshen.weixin.mapper.ManageAcademyMapper;
import com.jinqshen.weixin.pojo.table.Academy;
import com.jinqshen.weixin.service.ManageAcademyService;
@Service
@Transactional
public class ManageAcademyServiceImpl implements ManageAcademyService {

	@Autowired
	private ManageAcademyMapper manageAcademyMapper;
	
	@Override
	public List<Academy> findAllAcademy() {
		List<Academy> academies = manageAcademyMapper.findAllAcademyInfo();
		return academies;
	}

}
