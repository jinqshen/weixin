package com.jinqshen.weixin.pojo.table;

import java.io.Serializable;

/**
 * 课外锻炼活动实体类
 * @author jinqshen
 */
public class ExtraExercise implements Serializable {

    //课外锻炼活动班号
    private Integer extra_exercise_no;

    //锻炼项目
    private String project;

    //介绍
    private String introduce;

    //创建时间
    private String createTime;

    //开始时间
    private String beginTime;

    //结束时间
    private String endTime;

    //指导老师
    private String teacher;

    //容量
    private int capacity;

    //地点
    private String location;

    //状态
    private String status;

    public Integer getExtra_exercise_no() {
        return extra_exercise_no;
    }

    public void setExtra_exercise_no(Integer extra_exercise_no) {
        this.extra_exercise_no = extra_exercise_no;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
