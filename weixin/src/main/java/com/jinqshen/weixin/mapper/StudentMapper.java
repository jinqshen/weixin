package com.jinqshen.weixin.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.jinqshen.weixin.pojo.StudentAccount;

@Repository
public interface StudentMapper {

	public StudentAccount findAccount(@Param("student_number")String student_number, @Param("studentPassword_md5")String studentPassword_md5);
	
	public void registAccount(@Param("student_number")String student_number, @Param("studentPassword_md5")String studentPassword_md5);
}
