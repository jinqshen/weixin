package com.jinqshen.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinqshen.weixin.mapper.InformationMapper;
import com.jinqshen.weixin.pojo.Information;
import com.jinqshen.weixin.service.InformationService;
import com.jinqshen.weixin.vo.PageBean;
@Service
@Transactional
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationMapper informationMapper;
	
	@Override
	public List<Information> findAllInfo() {
		
		return informationMapper.findAll();
	}

	@Override
	public PageBean<Information> findInfoByPage(int currentPage) {
		PageBean<Information> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		int totalSize = informationMapper.findAllCount();
		pageBean.setTotalSize(totalSize);
		int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
		pageBean.setTotalPage(totalPage);
		List<Information> informations = informationMapper.findByLimit((currentPage-1)*pageBean.getPageSize(),pageBean.getPageSize());
		pageBean.setInformations(informations);
		return pageBean;
	}

	@Override
	public void deleteInfoByFinacono(int finacono) {
		informationMapper.deleteInfoByFinacono(finacono);
	}

	@Override
	public Information selectInfoByFinacono(int finacono) {
		return informationMapper.selectInfoByFinacono(finacono);
	}

}
