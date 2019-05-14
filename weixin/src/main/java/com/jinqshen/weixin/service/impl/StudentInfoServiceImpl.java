package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.StudentInfoMapper;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public List<String> getAllGrade() {
        return studentInfoMapper.findAllGradeFromStudentNumber();
    }

    @Override
    public StudentInfo findStudentInfo(int student_number) {
        return studentInfoMapper.findStudentInfoByStudentNumber(student_number);
    }
}
