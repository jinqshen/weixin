package com.jinqshen.weixin.service;
/**
 * 学生登录注册 服务层
 */

import com.jinqshen.weixin.pojo.StudentAccount;

public interface StudentService {

	/**
	 * 学生登录验证
	 * @param student_number 学号
	 * @param student_password 密码
	 * @return
	 */
	public StudentAccount findStudentAccount(Integer student_number, String student_password);

	/**
	 * 学生注册
	 * @param student_number 学号
	 * @param student_password 密码
	 */
	public void registStudentAccount(Integer student_number, String student_password);

	/**
	 * 学生修改密码
	 * @param student_number 学号
	 * @param newpassword 新密码
	 * @return
	 */
	public String alterPassword(Integer student_number,String newpassword);
}
