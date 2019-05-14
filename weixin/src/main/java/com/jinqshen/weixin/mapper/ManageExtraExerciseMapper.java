package com.jinqshen.weixin.mapper;

import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageExtraExerciseMapper {
    /**
     * 返回所有的课外锻炼活动
     * @return
     */
    public List<ExtraExercise> findAllExtraExercise();

    /**
     * 查找符合条件的课外锻炼活动的数目
     * @param status 状态
     * @param teacher 老师
     * @return
     */
    public int findCountOfExtraExercises(@Param("status") String status,@Param("teacher") String teacher);

    /**
     * 查找符合条件的课外锻炼活动
     * @param beginNumber 起始序号
     * @param pageSize 页面Size
     * @param status 状态
     * @param teacher 老师
     * @return
     */
    public List<ExtraExercise> findExtraExercises(@Param("beginNumber") int beginNumber,@Param("pageSize") int pageSize,@Param("status") String status,@Param("teacher") String teacher);

    /**
     * 删除加入此课外锻炼的所有记录
     * @param extra_exercise_no 课外锻炼活动序号
     */
    public void deleteJoinedRecord(@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 删除课外锻炼活动
     * @param extra_exercise_no 课外锻炼活动序号
     */
    public void deleteExtraExercise(@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 查询课外锻炼活动
     * @param extra_exercise_no 课外锻炼活动序号
     * @return
     */
    public ExtraExercise findTheExtraExercise(@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 插入课外锻炼活动
     * @param extraExercise 课外锻炼活动对象
     */
    public void insertExtraExercise(ExtraExercise extraExercise);

    /**
     * 更新课外锻炼活动
     * @param extraExercise 课外锻炼活动对象
     */
    public void updateTheExtraExercise(ExtraExercise extraExercise);

    /**
     * 查询已经参与该课外锻炼活动的人数
     * @param extra_exercise_no
     * @return
     */
    public Integer findJoinedCountOfStudent(@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 查询课外锻炼活动的参与人员名单
     * @param beginNumber 开始序号
     * @param pageSize 页面Size
     * @param extra_exercise_no 课外锻炼活动序号
     * @return
     */
    public List<StudentInfo> findJoinedStudents(@Param("beginNumber") int beginNumber,@Param("pageSize") int pageSize,@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 查询课外锻炼活动的所有参与人员名单
     * @param extra_exercise_no 课外锻炼活动序号
     * @return
     */
    public List<StudentInfo> findAllJoinedStudents(@Param("extra_exercise_no") int extra_exercise_no);
}
