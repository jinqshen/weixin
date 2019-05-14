package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.table.Academy;
import com.jinqshen.weixin.pojo.table.OrderFinaco;
import com.jinqshen.weixin.vo.PageBean;

import java.util.List;


/**
 * 学生体测预约服务层
 */
public interface StudentOrderFinacoService {

    /**
     * 根据条件查询符合的体侧预约搬信息
     * @param currentPage
     * @param student_number
     * @param for_academy
     * @return
     */
    public PageBean<OrderFinaco> getOrderFinacoByStudentNumber(int currentPage,int student_number,String for_academy);

    /**
     * 查询学生加入的体测预约班号
     * @param student_number
     * @return
     */
    public Integer findJoinedOrderFinaco(int student_number);

    /**
     * 学生预约体测
     * @param student_number 学号
     * @param order_class_no 体测课程号
     * @return
     */
    public String orderTheFinaco(int student_number,int order_class_no);

    /**
     * 学生取消体测预约
     * @param student_number 学号
     * @param order_class_no 体测课程号
     * @return
     */
    public String cancelOrderTheFinaco(int student_number,int order_class_no);

    /**
     * 获取所有的学院
     * @return
     */
    public List<Academy> findAllAcademies();
}
