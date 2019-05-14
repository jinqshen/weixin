package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.vo.PageBean;

import java.util.List;

/**
 * 学生端  课外锻炼班服务层
 */
public interface StudentExtraExerciseService {

    /**
     * 查询符合条件的课外锻炼活动
     * @param currentPage 当前页
     * @param status 活动状态
     * @return
     */
    public PageBean<ExtraExercise> getExtraExercises(int currentPage, String status);

    /**
     * 学生加入课外锻炼活动
     * @param student_number 学生学号
     * @param extra_exercise_no 课外锻炼序号
     * @return
     */
    public String studentJoinExercise(int student_number,int extra_exercise_no);

    /**
     * 学生退出课外锻炼活动
     * @param student_number 学生学号
     * @param extra_exercise_no 课外锻炼序号
     * @return
     */
    public String studentExitExercise(int student_number,int extra_exercise_no);

    /**
     * 查询学生已加入的课外锻炼序号
     * @param student_number 学生学号
     * @return
     */
    public List<Integer> getJoinedExtraExercise(int student_number);
}
