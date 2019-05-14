package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.vo.PageBean;

import java.util.List;

/**
 * 课外锻炼服务层
 * @author jinqshen
 */
public interface ManageExtraExerciseService {

    /**
     * 获取所有的课外锻炼活动
     * @return
     */
    public List<ExtraExercise> getAllExtraExercise();

    /**
     * 获取符合条件的课外锻炼活动
     * @param currentPage 当前页
     * @param status 课外锻炼状态
     * @param teacher 老师
     * @return
     */
    public PageBean<ExtraExercise> findExtraExercises(int currentPage,String status,String teacher);

    /**
     * 删除课外锻炼活动
     * @param extra_exercise_no 课外锻炼活动序号
     * @return
     */
    public String removeTheExtraExercise(int extra_exercise_no);

    /**
     * 获取课外锻炼活动信息
     * @param extra_exercise_no 课外锻炼活动序号
     * @return
     */
    public ExtraExercise findExtraExercise(int extra_exercise_no);

    /**
     * 插入一个新的课外锻炼活动
     * @param extraExercise 课外锻炼活动对象
     * @return
     */
    public String insertOneExtraExercise(ExtraExercise extraExercise);

    /**
     * 更新一个已存在的课外的锻炼活动
     * @param extraExercise 课外锻炼活动对象
     * @return
     */
    public String updateExtraExercise(ExtraExercise extraExercise);

    /**
     * 查询加入该课外锻炼活动的人数
     * @param extra_exercise_no
     * @return
     */
    public Integer findJoinedCountOfStudent(int extra_exercise_no);

    /**
     * 查找参加某课外锻炼活动的学生名单
     * @param currentPage
     * @param extra_exercise_no
     * @return
     */
    public PageBean<StudentInfo> findJoinedStudentInfos(int currentPage, int extra_exercise_no);

    /**
     * 查找参加某课外锻炼活动的所有学生名单
     * @param extra_exercise_no
     * @return
     */
    public List<StudentInfo> findAllJoinedStudentInfos(int extra_exercise_no);
}
