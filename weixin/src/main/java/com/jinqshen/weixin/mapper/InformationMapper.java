package com.jinqshen.weixin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinqshen.weixin.pojo.Information;
@Repository
public interface InformationMapper {

	public List<Information> findAll();

	public Integer findAllCount();
	
	public List<Information> findByLimit(@Param("currentPage")int currentPage,@Param("pageSize")int pageSize);

	public void deleteInfoByFinacono(@Param("finacono")int finacono);
	
	public Information selectInfoByFinacono(@Param("finacono")int finacono);

	public void updataFinaco(@Param("finacono")Integer finacono, @Param("studentnumber")String studentnumber, @Param("project")String project, @Param("grade")float grade, @Param("isqualified")boolean isqualified);
}
