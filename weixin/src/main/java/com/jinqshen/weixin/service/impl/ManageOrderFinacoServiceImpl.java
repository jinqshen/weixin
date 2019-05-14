package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.ManageOrderFinacoMapper;
import com.jinqshen.weixin.pojo.table.OrderFinaco;
import com.jinqshen.weixin.service.ManageOrderFinacoService;
import com.jinqshen.weixin.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageOrderFinacoServiceImpl implements ManageOrderFinacoService {

    @Autowired
    private ManageOrderFinacoMapper manageOrderFinacoMapper;

    @Override
    public PageBean<OrderFinaco> getOrderFinacos(int currentPage, String for_grade, String for_academy) {
        PageBean<OrderFinaco> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        int totalSize = manageOrderFinacoMapper.findCountOfOrderFinacos(for_grade,for_academy);
        pageBean.setTotalSize(totalSize);
        int totalPage = (int) Math.ceil(totalSize*1.0/pageBean.getPageSize());
        pageBean.setTotalPage(totalPage);
        List<OrderFinaco> list = manageOrderFinacoMapper.findOrderFinacos((currentPage - 1)*pageBean.getPageSize(),pageBean.getPageSize(),for_grade,for_academy);
        pageBean.setInformations(list);
        return pageBean;
    }

    @Override
    public String deleteOrderFinaco(int order_class_no) {
        String deleteMsg = "删除成功";
        try {
            manageOrderFinacoMapper.deleteStudentFinacoByOrderClassNo(order_class_no);
            manageOrderFinacoMapper.deleteOrderFinacoByOrderClassNo(order_class_no);
        }catch(Exception e){
            deleteMsg = "删除失败";
        }
        return deleteMsg;
    }

    @Override
    public OrderFinaco getOrderFinaco(int order_class_no) {
        OrderFinaco orderFinaco = manageOrderFinacoMapper.findOrderFinaco(order_class_no);
        return orderFinaco;
    }

    @Override
    public String updateOrderFinaco(OrderFinaco orderFinaco) {
        String updateMsg = "更新成功";
        try{
            OrderFinaco orderFinaco1 = manageOrderFinacoMapper.findOrderFinacoByGradeAndAcademy(orderFinaco.getFor_grade(),orderFinaco.getFor_academy());
            if(orderFinaco1 != null && orderFinaco1.getOrder_class_no() != orderFinaco.getOrder_class_no()){
                updateMsg = "针对年级针对学院的体测班已存在";
            }else {
                manageOrderFinacoMapper.updateOrderFinaco(orderFinaco);
            }
        }catch (Exception e){
            updateMsg = "更新失败";
        }
        return updateMsg;
    }

    @Override
    public String newOrderFinacoClass(OrderFinaco orderFinaco) {
        String insertMsg = orderFinaco.getFor_grade() + "级" + orderFinaco.getFor_academy() + "体测预约班新增成功";
        try{
            OrderFinaco orderFinaco1 = manageOrderFinacoMapper.findOrderFinacoByGradeAndAcademy(orderFinaco.getFor_grade(), orderFinaco.getFor_academy());
            if(orderFinaco1 != null){
                insertMsg = orderFinaco.getFor_grade() + "级" + orderFinaco.getFor_academy() + "体测预约班已存在";
            }else {
                manageOrderFinacoMapper.insertOrderFinaco(orderFinaco);
            }
        }catch (Exception e){
            insertMsg = orderFinaco.getFor_grade() + "级" + orderFinaco.getFor_academy() + "体测预约班新增失败";
        }
        return insertMsg;
    }

    @Override
    public List<OrderFinaco> getAllOrderFinaco() {
        return manageOrderFinacoMapper.findAllOrderFinaco();
    }
}
