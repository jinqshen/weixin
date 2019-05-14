package com.jinqshen.weixin.pojo.table;

/**
 * 体测预约班实体类
 * @author jinqshen
 */
public class OrderFinaco {

    //体测预约班号
    private int order_class_no;

    //针对年级
    private String for_grade;

    //针对学院
    private String for_academy;

    //预约开始时间
    private String order_beginTime;

    //预约结束时间
    private String order_endTime;

    //创建时间
    private String createdTime;

    //状态
    private String status;

    //体测开始时间
    private String finaco_beginTime;

    //体测结束时间
    private String finaco_endTime;

    //体测地点
    private String finaco_location;

    //体测描述
    private String finaco_describe;

    public int getOrder_class_no() {
        return order_class_no;
    }

    public void setOrder_class_no(int order_class_no) {
        this.order_class_no = order_class_no;
    }

    public String getFor_grade() {
        return for_grade;
    }

    public void setFor_grade(String for_grade) {
        this.for_grade = for_grade;
    }

    public String getFor_academy() {
        return for_academy;
    }

    public void setFor_academy(String for_academy) {
        this.for_academy = for_academy;
    }

    public String getOrder_beginTime() {
        return order_beginTime;
    }

    public void setOrder_beginTime(String order_beginTime) {
        this.order_beginTime = order_beginTime;
    }

    public String getOrder_endTime() {
        return order_endTime;
    }

    public void setOrder_endTime(String order_endTime) {
        this.order_endTime = order_endTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinaco_beginTime() {
        return finaco_beginTime;
    }

    public void setFinaco_beginTime(String finaco_beginTime) {
        this.finaco_beginTime = finaco_beginTime;
    }

    public String getFinaco_endTime() {
        return finaco_endTime;
    }

    public void setFinaco_endTime(String finaco_endTime) {
        this.finaco_endTime = finaco_endTime;
    }

    public String getFinaco_location() {
        return finaco_location;
    }

    public void setFinaco_location(String finaco_location) {
        this.finaco_location = finaco_location;
    }

    public String getFinaco_describe() {
        return finaco_describe;
    }

    public void setFinaco_describe(String finaco_describe) {
        this.finaco_describe = finaco_describe;
    }
}
