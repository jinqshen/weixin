package com.jinqshen.weixin.schedule;

import com.jinqshen.weixin.pojo.table.ExtraExercise;
import com.jinqshen.weixin.pojo.table.OrderFinaco;
import com.jinqshen.weixin.service.ManageExtraExerciseService;
import com.jinqshen.weixin.service.ManageOrderFinacoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Extra_Exercise_Status {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ManageExtraExerciseService manageExtraExerciseService;

    @Autowired
    private ManageOrderFinacoService manageOrderFinacoService;

    /**
     * 定时任务 10m执行一次，检测所有的课外活动状态并更新
     */
    @Scheduled(fixedRate = 600000)
    public void updateExtraExerciseStatus(){
        List<ExtraExercise> ExtraExercises = manageExtraExerciseService.getAllExtraExercise();
        for (ExtraExercise extraExercise  : ExtraExercises) {
            String beginTime = extraExercise.getBeginTime();
            String endTime = extraExercise.getEndTime();
            Date date = new Date();
            long currentTime = date.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date bTime = dateFormat.parse(beginTime);
                Date eTime = dateFormat.parse(endTime);
                if(currentTime < bTime.getTime() && !extraExercise.getStatus().equals("报名中")){
                    String oldstatus = extraExercise.getStatus();
                    extraExercise.setStatus("报名中");
                    manageExtraExerciseService.updateExtraExercise(extraExercise);
                    logger.info(extraExercise.getProject() + "状态由" + oldstatus + "更新为" + extraExercise.getStatus());
                }
                if(currentTime >= bTime.getTime() && currentTime <= eTime.getTime() && !extraExercise.getStatus().equals("进行中")){
                    String oldstatus = extraExercise.getStatus();
                    extraExercise.setStatus("进行中");
                    manageExtraExerciseService.updateExtraExercise(extraExercise);
                    logger.info(extraExercise.getProject() + "状态由" + oldstatus + "更新为" + extraExercise.getStatus());
                }
                if(currentTime > eTime.getTime() && !extraExercise.getStatus().equals("已结束")){
                    String oldstatus = extraExercise.getStatus();
                    extraExercise.setStatus("已结束");
                    manageExtraExerciseService.updateExtraExercise(extraExercise);
                    logger.info(extraExercise.getProject() + "状态由" + oldstatus + "更新为" + extraExercise.getStatus());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新体测预约班状态 10min执行一次
     */
    @Scheduled(fixedRate = 600000)
    public void updateFinacoStatus(){
        List<OrderFinaco> orderFinacos = manageOrderFinacoService.getAllOrderFinaco();
        if(orderFinacos != null){
            for (OrderFinaco orderFinaco : orderFinacos) {
                String beginTime = orderFinaco.getOrder_beginTime();
                String endTime = orderFinaco.getOrder_endTime();
                Date date = new Date();
                long currentTime = date.getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try{
                    Date bTime = dateFormat.parse(beginTime);
                    Date eTime = dateFormat.parse(endTime);
                    if(currentTime < bTime.getTime() && !orderFinaco.getStatus().equals("未开放预约")){
                        String oldstatus = orderFinaco.getStatus();
                        orderFinaco.setStatus("未开放预约");
                        manageOrderFinacoService.updateOrderFinaco(orderFinaco);
                        logger.info(orderFinaco.getFor_grade() + "级" + orderFinaco.getFor_academy() + "体测预约班状态由" + oldstatus + "更新为" + orderFinaco.getStatus());
                    }
                    if(currentTime > bTime.getTime() && currentTime < eTime.getTime() && !orderFinaco.getStatus().equals("体测预约中")){
                        String oldstatus = orderFinaco.getStatus();
                        orderFinaco.setStatus("体测预约中");
                        manageOrderFinacoService.updateOrderFinaco(orderFinaco);
                        logger.info(orderFinaco.getFor_grade() + "级" + orderFinaco.getFor_academy() + "体测预约班状态由" + oldstatus + "更新为" + orderFinaco.getStatus());
                    }
                    if(currentTime > eTime.getTime() && !orderFinaco.getStatus().equals("预约已结束")){
                        String oldstatus = orderFinaco.getStatus();
                        orderFinaco.setStatus("预约已结束");
                        manageOrderFinacoService.updateOrderFinaco(orderFinaco);
                        logger.info(orderFinaco.getFor_grade() + "级" + orderFinaco.getFor_academy() + "体测预约班状态由" + oldstatus + "更新为" + orderFinaco.getStatus());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
