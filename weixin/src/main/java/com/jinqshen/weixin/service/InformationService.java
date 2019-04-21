package com.jinqshen.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jinqshen.weixin.pojo.Information;
import com.jinqshen.weixin.vo.PageBean;

public interface InformationService {

	public List<Information> findAllInfo();
	
	public PageBean<Information> findInfoByPage(int currentPage);

	public void deleteInfoByFinacono(int finacono);

	public Information selectInfoByFinacono(int finacono);

}
