package com.jinqshen.weixin.service.impl;

import com.jinqshen.weixin.mapper.ManageMapper;
import com.jinqshen.weixin.pojo.table.ManageAccount;
import com.jinqshen.weixin.service.ManageService;
import com.jinqshen.weixin.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public ManageAccount findManageAccount(ManageAccount manageAccount) {
        String manage_username = manageAccount.getManage_username();
        String manage_password = manageAccount.getManage_password();
        ManageAccount theManageAccount = manageMapper.findTheManageAccount(manage_username, MD5Util.encrypt(manage_password));
        return theManageAccount;
    }

    @Override
    public String updatePassword(String manage_username, String newpassword) {
        String updateMsg = "修改成功";
        try{
            manageMapper.updateManagePassword(manage_username,MD5Util.encrypt(newpassword));
        }catch (Exception e){
            updateMsg = "修改失败";
        }
        return updateMsg;
    }

    @Override
    public String addManageAccount(ManageAccount manageAccount) {
        String insertMsg = "新增管理员成功";
        try{
            ManageAccount manageAccount1 = manageMapper.findTheManageAccountByUserName(manageAccount.getManage_username());
            if(manageAccount1 != null){
                insertMsg = "新增失败，该用户已存在！";
            }else {
                manageMapper.insertManageAccount(manageAccount.getManage_username(),MD5Util.encrypt(manageAccount.getManage_password()));
            }
        }catch (Exception e){
            insertMsg = "新增管理员失败";
        }
        return insertMsg;
    }
}
