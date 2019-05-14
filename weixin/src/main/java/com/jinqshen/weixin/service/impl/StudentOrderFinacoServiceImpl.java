package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.StudentOrderFinacoMapper;
import com.jinqshen.weixin.pojo.table.Academy;
import com.jinqshen.weixin.pojo.table.OrderFinaco;
import com.jinqshen.weixin.service.StudentOrderFinacoService;
import com.jinqshen.weixin.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentOrderFinacoServiceImpl implements StudentOrderFinacoService {

    @Autowired
    private StudentOrderFinacoMapper studentOrderFinacoMapper;

    @Override
    public PageBean<OrderFinaco> getOrderFinacoByStudentNumber(int currentPage, int student_number, String for_academy) {
        PageBean<OrderFinaco> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        int for_grade = student_number/1000000;
        int totalSize = studentOrderFinacoMapper.findCountOfOrderFinacoByGradeAndAcademy(for_grade + "",for_academy);
        pageBean.setTotalSize(totalSize);
        int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
        pageBean.setTotalPage(totalPage);
        List<OrderFinaco> list = studentOrderFinacoMapper.findOrderFinacoByGradeAndAcademy((currentPage - 1)*pageBean.getPageSize(),pageBean.getPageSize(),for_grade + "",for_academy);
        pageBean.setInformations(list);
        return pageBean;
    }

    @Override
    public Integer findJoinedOrderFinaco(int student_number) {
        return studentOrderFinacoMapper.findJoinedOrderFinacoNo(student_number);
    }

    @Override
    public String orderTheFinaco(int student_number, int order_class_no) {
        String insertMsg = "预约成功";
        try{
            studentOrderFinacoMapper.insertOrderTheFinaco(student_number,order_class_no);
        }catch (Exception e){
            insertMsg = "预约失败";
        }
        return insertMsg;
    }

    @Override
    public String cancelOrderTheFinaco(int student_number, int order_class_no) {
        String deleteMsg = "取消预约成功";
        try {
            studentOrderFinacoMapper.deleteOrderTheFinaco(student_number,order_class_no);
        }catch (Exception e){
            deleteMsg = "取消预约失败";
        }
        return deleteMsg;
    }

    @Override
    public List<Academy> findAllAcademies() {
        return studentOrderFinacoMapper.findAllAcademies();
    }
}
