package com.jinqshen.weixin.mapper;

import com.jinqshen.weixin.pojo.table.StudentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生信息 持久层
 */
@Repository
public interface StudentInfoMapper {

    /**
     * 获取所有的年级
     * @return
     */
    public List<String> findAllGradeFromStudentNumber();

    /**
     * 查询学生信息
     * @param student_number 学号
     * @return
     */
    public StudentInfo findStudentInfoByStudentNumber(@Param("student_number") int student_number);
}
