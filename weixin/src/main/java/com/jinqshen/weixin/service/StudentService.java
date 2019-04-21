package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.StudentAccount;

public interface StudentService {

	public StudentAccount findStudentAccount(String student_number, String student_password);
	
	public void registStudentAccount(String student_number, String student_password);
}
