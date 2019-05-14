package com.jinqshen.weixin.mapper;

import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.vo.Transcript;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生成绩单 持久层
 */
@Repository
public interface StudentTranscriptMapper {

    /**
     * 查询某学生(男)的某项体测成绩
     * @param student_number 学号
     * @param project_name 体测项目名
     * @return
     */
    public Transcript findTheProjectTranscript_b(@Param("student_number") int student_number, @Param("project_name") String project_name,@Param("semester") String semester);

    /**
     * 查询某学生体测的学期
     * @param student_number 学号
     * @return
     */
    public List<String> findExistSemester(@Param("student_number") int student_number);

    /**
     * 查询某学生
     * @param student_number
     * @return
     */
    public StudentInfo findStudent(@Param("student_number") int student_number);

    /**
     * 通过BMI指数查询标准表(男)获得Level
     * @param bmi bmi指数
     * @return
     */
    public String findBMILevel_b(@Param("bmi") float bmi);

    /**
     * 通过肺活量体重指数查询标准表(男)获得Level
     * @param vital_capacit_value 肺活量体重指数
     * @return
     */
    public String findVital_CapacitLevel_b(@Param("vital_capacit_value") float vital_capacit_value);

    /**
     * 通过肺活量体重指数查询标准表(男)获得Score
     * @param vital_capacity_value 肺活量体重指数
     * @return
     */
    public Integer findVital_CapacitScore_b(@Param("vital_capacit_value") float vital_capacity_value);

    /**
     * 查询某学生(女)的某项体测成绩
     * @param student_number 学号
     * @param project_name 体测项目名
     * @return
     */
    public Transcript findTheProjectTranscript_g(@Param("student_number") int student_number, @Param("project_name") String project_name,@Param("semester") String semester);

    /**
     * 通过BMI指数查询标准表(女)获得Level
     * @param bmi bmi指数
     * @return
     */
    public String findBMILevel_g(@Param("student_number") float bmi);

    /**
     * 通过肺活量体重指数查询标准表(女)获得Level
     * @param vital_capacit_value 肺活量体重指数
     * @return
     */
    public String findVital_CapacitLevel_g(@Param("vital_capacit_value") float vital_capacit_value);

    /**
     * 通过肺活量体重指数查询标准表(女)获得Score
     * @param vital_capacit_value 肺活量体重指数
     * @return
     */
    public Integer findVital_CapacitScore_g(@Param("vital_capacit_value") float vital_capacit_value);
}
