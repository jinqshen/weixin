package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.table.StudentInfo;

import java.util.List;

public interface StudentInfoService {

    /**
     * 获取所有已存在的学生的年级
     * @return
     */
    public List<String> getAllGrade();

    /**
     * 通过学号获取学生信息
     * @param student_number
     * @return
     */
    public StudentInfo findStudentInfo(int student_number);
}
