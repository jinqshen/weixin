package com.jinqshen.weixin.vo;

/**
 * 成绩单Vo
 */
public class Transcript {

    public Transcript(Integer student_number, String name, String project_name,Integer score, String test_result_describe, String level) {
        this.student_number = student_number;
        this.name = name;
        this.project_name = project_name;
        this.test_result_describe = test_result_describe;
        this.score = score;
        this.level = level;
    }

    //学号
    private Integer student_number;

    //姓名
    private String name;

    //体测项目名
    private String project_name;

    //测试结果
    private String test_result_describe;

    //得分
    private Integer score;

    //等级
    private String level;

    public Integer getStudent_number() {
        return student_number;
    }

    public void setStudent_number(Integer student_number) {
        this.student_number = student_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTest_result_describe() {
        return test_result_describe;
    }

    public void setTest_result_describe(String test_result_describe) {
        this.test_result_describe = test_result_describe;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
