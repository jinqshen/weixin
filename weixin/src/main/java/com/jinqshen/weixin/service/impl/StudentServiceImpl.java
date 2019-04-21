package com.jinqshen.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinqshen.weixin.mapper.StudentMapper;
import com.jinqshen.weixin.pojo.StudentAccount;
import com.jinqshen.weixin.service.StudentService;
import com.jinqshen.weixin.utils.MD5Util;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public StudentAccount findStudentAccount(String student_number, String student_password) {
		String studentPassword_md5 = MD5Util.encrypt(student_password);
		return studentMapper.findAccount(student_number, studentPassword_md5);
	}

	@Override
	public void registStudentAccount(String student_number, String student_password) {
		String studentPassword_md5 = MD5Util.encrypt(student_password);
		StudentAccount studentAccount = new StudentAccount();
		studentAccount.setStudent_number(student_number);
		studentAccount.setStudent_password(studentPassword_md5);
		studentMapper.registAccount(student_number, studentPassword_md5);
	}

}
