package com.jinqshen.weixin.mapper;
/**
 * 学生端 课外锻炼活动持久层
 */

import com.jinqshen.weixin.pojo.table.ExtraExercise;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface StudentExtraExerciseMapper {

    /**
     * 查询符合状态的课外锻炼活动的数目
     * @param status 状态
     * @return
     */
    public int findCountOfExtraExercises(@Param("status") String status);

    /**
     * 查询符合状态的课外锻炼活动
     * @param beginNumber 起始序号
     * @param pageSize 页面Size
     * @param status 状态
     * @return
     */
    public List<ExtraExercise> findExtraExercises(@Param("beginNumber") int beginNumber,@Param("pageSize") int pageSize,@Param("status") String status);

    /**
     * 插入一条加入课外活动数据
     * @param student_number 学生学号
     * @param extra_exercise_no 课外锻炼序号
     */
    public void insertStudent_Exercise(@Param("student_number") int student_number,@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 删除一条加入课外活动数据
     * @param student_number 学生学号
     * @param extra_exercise_no 课外锻炼序号
     */
    public void deleteStudent_Exercise(@Param("student_number") int student_number,@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 查询课外锻炼的容量
     * @param extra_exercise_no 课外锻炼序号
     * @return
     */
    public int findCapacityOfExtraExercise(@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 查询课外锻炼已经加入的人数
     * @param extra_exercise_no
     * @return
     */
    public int findSizeOfJoined(@Param("extra_exercise_no") int extra_exercise_no);

    /**
     * 查询已经加入的课外锻炼序号
     * @param student_number
     * @return
     */
    public List<Integer> findJoinedExtraExercise(@Param("student_number") int student_number);
}
