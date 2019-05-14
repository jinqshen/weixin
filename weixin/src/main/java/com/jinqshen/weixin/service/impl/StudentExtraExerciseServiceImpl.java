package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.StudentExtraExerciseMapper;
import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.service.StudentExtraExerciseService;
import com.jinqshen.weixin.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentExtraExerciseServiceImpl implements StudentExtraExerciseService {

    @Autowired
    private StudentExtraExerciseMapper studentExtraExerciseMapper;

    @Override
    public PageBean<ExtraExercise> getExtraExercises(int currentPage, String status) {
        PageBean<ExtraExercise> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        int totalSize = studentExtraExerciseMapper.findCountOfExtraExercises(status);
        pageBean.setTotalSize(totalSize);
        int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
        pageBean.setTotalPage(totalPage);
        List<ExtraExercise> list = studentExtraExerciseMapper.findExtraExercises((currentPage - 1)*pageBean.getPageSize(),pageBean.getPageSize(),status);
        pageBean.setInformations(list);
        return pageBean;
    }

    @Override
    public String studentJoinExercise(int student_number, int extra_exercise_no) {
        String joinMsg = "加入成功";
        try{
            int capacityOfExtraExercise = studentExtraExerciseMapper.findCapacityOfExtraExercise(extra_exercise_no);
            int sizeOfJoined = studentExtraExerciseMapper.findSizeOfJoined(extra_exercise_no);
            if(sizeOfJoined < capacityOfExtraExercise) {
                studentExtraExerciseMapper.insertStudent_Exercise(student_number, extra_exercise_no);
            }else {
                joinMsg = "该课外锻炼活动容量已满";
            }
        }catch (Exception e){
            joinMsg = "加入失败";
        }
        return joinMsg;
    }

    @Override
    public String studentExitExercise(int student_number, int extra_exercise_no) {
        String exitMsg = "退出成功";
        try{
            studentExtraExerciseMapper.deleteStudent_Exercise(student_number,extra_exercise_no);
        }catch (Exception e){
            exitMsg = "退出失败";
        }
        return exitMsg;
    }

    @Override
    public List<Integer> getJoinedExtraExercise(int student_number) {
        List<Integer> list = new ArrayList<>();
        list = studentExtraExerciseMapper.findJoinedExtraExercise(student_number);
        return list;
    }
}
