package com.jinqshen.weixin.mapper;
/**
 * 学生账户  持久层
 */

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.jinqshen.weixin.pojo.StudentAccount;

@Repository
public interface StudentMapper {

	/**
	 * 查找学生账户
	 * @param student_number 学号
	 * @param studentPassword_md5 密码
	 * @return
	 */
	public StudentAccount findAccount(@Param("student_number")Integer student_number, @Param("studentPassword_md5")String studentPassword_md5);

	/**
	 * 新增学生账户
	 * @param student_number 学号
	 * @param studentPassword_md5 密码
	 */
	public void registAccount(@Param("student_number")Integer student_number, @Param("studentPassword_md5")String studentPassword_md5);

	/**
	 * 更新学生账户
	 * @param student_number 学号
	 * @param studentPassword_md5 密码
	 */
	public void updateStudentAccount(@Param("student_number")Integer student_number, @Param("studentPassword_md5")String studentPassword_md5);
}
