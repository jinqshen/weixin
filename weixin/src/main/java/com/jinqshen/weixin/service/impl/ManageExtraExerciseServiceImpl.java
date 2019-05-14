package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.ManageExtraExerciseMapper;
import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.pojo.table.StudentInfo;
import com.jinqshen.weixin.service.ManageExtraExerciseService;
import com.jinqshen.weixin.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManageExtraExerciseServiceImpl implements ManageExtraExerciseService {
    @Autowired
    private ManageExtraExerciseMapper manageExtraExerciseMapper;

    @Override
    public List<ExtraExercise> getAllExtraExercise() {
        return manageExtraExerciseMapper.findAllExtraExercise();
    }

    @Override
    public PageBean<ExtraExercise> findExtraExercises(int currentPage, String status, String teacher) {
        PageBean<ExtraExercise> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        int totalSize = manageExtraExerciseMapper.findCountOfExtraExercises(status,teacher);
        pageBean.setTotalSize(totalSize);
        int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
        pageBean.setTotalPage(totalPage);
        List<ExtraExercise> list  = manageExtraExerciseMapper.findExtraExercises((currentPage-1)*pageBean.getPageSize(),pageBean.getPageSize(),status,teacher);
        pageBean.setInformations(list);
        return pageBean;
    }

    @Override
    public String removeTheExtraExercise(int extra_exercise_no) {
        String deleteMsg = "删除成功";
        try{
            manageExtraExerciseMapper.deleteJoinedRecord(extra_exercise_no);
            manageExtraExerciseMapper.deleteExtraExercise(extra_exercise_no);
        }catch (Exception e){
            deleteMsg = "删除失败";
        }
        return deleteMsg;
    }

    @Override
    public ExtraExercise findExtraExercise(int extra_exercise_no) {
        return manageExtraExerciseMapper.findTheExtraExercise(extra_exercise_no);
    }

    @Override
    public String insertOneExtraExercise(ExtraExercise extraExercise) {
        String insertMsg = "新增成功";
        try{
            manageExtraExerciseMapper.insertExtraExercise(extraExercise);
        }catch (Exception e){
            insertMsg = "新增失败";
        }
        return insertMsg;
    }

    @Override
    public String updateExtraExercise(ExtraExercise extraExercise) {
        String updateMsg = "更新成功";
        try{
            manageExtraExerciseMapper.updateTheExtraExercise(extraExercise);
        }catch (Exception e){
            updateMsg = "更新失败";
        }
        return updateMsg;
    }

    @Override
    public Integer findJoinedCountOfStudent(int extra_exercise_no) {
        return manageExtraExerciseMapper.findJoinedCountOfStudent(extra_exercise_no);
    }

    @Override
    public PageBean<StudentInfo> findJoinedStudentInfos(int currentPage, int extra_exercise_no) {
        PageBean<StudentInfo> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        Integer totalSize = manageExtraExerciseMapper.findJoinedCountOfStudent(extra_exercise_no);
        pageBean.setTotalSize(totalSize);
        int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
        pageBean.setTotalPage(totalPage);
        List<StudentInfo> list = manageExtraExerciseMapper.findJoinedStudents((currentPage-1)*pageBean.getPageSize(),pageBean.getPageSize(),extra_exercise_no);
        pageBean.setInformations(list);
        return pageBean;
    }

    @Override
    public List<StudentInfo> findAllJoinedStudentInfos(int extra_exercise_no) {
        return manageExtraExerciseMapper.findAllJoinedStudents(extra_exercise_no);
    }
}
