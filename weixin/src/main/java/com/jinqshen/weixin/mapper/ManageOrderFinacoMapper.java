package com.jinqshen.weixin.mapper;

import com.jinqshen.weixin.pojo.table.OrderFinaco;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 体测预约班管理持久层
 */
@Repository
public interface ManageOrderFinacoMapper {

    /**
     * 查询符合条件的体测预约班数目
     * @param for_grade 年级
     * @param for_academy 学院
     * @return
     */
    public int findCountOfOrderFinacos(@Param("for_grade") String for_grade, @Param("for_academy") String for_academy);

    /**
     * 查询符合条件的体测预约班
     * @param beginNumber 开始序号
     * @param pageSize 页面Size
     * @param for_grade 年级
     * @param for_academy 学院
     * @return
     */
    public List<OrderFinaco> findOrderFinacos(@Param("beginNumber") int beginNumber,@Param("pageSize") int pageSize,@Param("for_grade") String for_grade, @Param("for_academy") String for_academy);

    /**
     * 删除指定体测预约班的预约记录
     * @param order_class_no 体测预约班序号
     */
    public void deleteStudentFinacoByOrderClassNo(@Param("order_class_no") int order_class_no);

    /**
     * 删除指定体测预约班
     * @param order_class_no 体测预约班序号
     */
    public void deleteOrderFinacoByOrderClassNo(@Param("order_class_no") int order_class_no);

    /**
     * 查找指定体测预约班信息
     * @param order_class_no 体测预约班序号
     * @return
     */
    public OrderFinaco findOrderFinaco(@Param("order_class_no") int order_class_no);

    /**
     * 查找指定体测预约班信息
     * @param for_grade 针对年级
     * @param for_academy 针对学院
     * @return
     */
    public OrderFinaco findOrderFinacoByGradeAndAcademy(@Param("for_grade") String for_grade,@Param("for_academy") String for_academy);

    /**
     * 更新体测预约班信息
     * @param orderFinaco
     */
    public void updateOrderFinaco(OrderFinaco orderFinaco);

    /**
     * 新增体测预约班
     * @param orderFinaco
     */
    public void insertOrderFinaco(OrderFinaco orderFinaco);

    /**
     * 获取所有的体测预约班
     * @return
     */
    public List<OrderFinaco> findAllOrderFinaco();
}
