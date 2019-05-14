package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.table.OrderFinaco;
import com.jinqshen.weixin.vo.PageBean;

import java.util.List;

/**
 * 体测预约服务层
 */
public interface ManageOrderFinacoService {

    /**
     * 查询符合条件的体测预约班
     * @param currentPage 当前页码
     * @param for_grade 年级
     * @param for_academy 学院
     * @return
     */
    public PageBean<OrderFinaco> getOrderFinacos(int currentPage, String for_grade, String for_academy);

    /**
     * 删除指定体测预约班
     * @param order_class_no 体测预约班序号
     * @return
     */
    public String deleteOrderFinaco(int order_class_no);

    /**
     * 得到指定体测预约班信息
     * @param order_class_no 体测预约班号
     * @return
     */
    public OrderFinaco getOrderFinaco(int order_class_no);

    /**
     * 更新指定体测预约班信息
     * @param orderFinaco
     * @return
     */
    public String updateOrderFinaco(OrderFinaco orderFinaco);

    /**
     * 新增体测预约班
     * @param orderFinaco
     * @return
     */
    public String newOrderFinacoClass(OrderFinaco orderFinaco);

    /**
     * 获取所有的体测预约班
     * @return
     */
    public List<OrderFinaco> getAllOrderFinaco();
}
