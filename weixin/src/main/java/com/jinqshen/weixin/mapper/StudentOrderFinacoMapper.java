package com.jinqshen.weixin.mapper;

import com.jinqshen.weixin.pojo.table.Academy;
import com.jinqshen.weixin.pojo.table.OrderFinaco;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentOrderFinacoMapper {

    /**
     * 通过年级查询体测预约班
     * @param for_grade
     * @return
     */
    public List<OrderFinaco> findOrderFinacoByGrade(@Param("for_grade") String for_grade);

    /**
     * 通过年级和学院查询体测预约班数目
     * @param for_grade
     * @param for_academy
     * @return
     */
    public int findCountOfOrderFinacoByGradeAndAcademy(@Param("for_grade") String for_grade,@Param("for_academy") String for_academy);

    /**
     * 通过年级和学院查询体测预约班信息
     * @param beginNumber
     * @param pageSize
     * @param for_grade
     * @param for_academy
     * @return
     */
    public List<OrderFinaco> findOrderFinacoByGradeAndAcademy(@Param("beginNumber") int beginNumber,@Param("pageSize") int pageSize,@Param("for_grade") String for_grade,@Param("for_academy") String for_academy);

    /**
     * 根据学号查询已选择的体测预约班号
     * @param student_number
     * @return
     */
    public Integer findJoinedOrderFinacoNo(@Param("student_number") int student_number);

    /**
     * 插入一条预约记录
     * @param student_number 学号
     * @param order_class_no 体测课程号
     */
    public void insertOrderTheFinaco(@Param("student_number") int student_number,@Param("order_class_no") int order_class_no);

    /**
     * 删除一条预约记录
     * @param student_number
     * @param order_class_no
     */
    public void deleteOrderTheFinaco(@Param("student_number") int student_number,@Param("order_class_no") int order_class_no);

    /**
     * 获取所有的学院
     * @return
     */
    public List<Academy> findAllAcademies();
}
