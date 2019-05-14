package com.jinqshen.weixin.service;

import com.jinqshen.weixin.pojo.table.ManageAccount;

/**
 * 后台账户管理服务层
 */
public interface ManageService {

    /**
     * 查询管理账户
     * @param manageAccount 管理账户对象
     * @return
     */
    public ManageAccount findManageAccount(ManageAccount manageAccount);

    /**
     * 修改密码
     * @param manage_username 用户名
     * @param newpassword 新密码
     * @return
     */
    public String updatePassword(String manage_username,String newpassword);

    /**
     * 新增管理员用户
     * @param manageAccount
     * @return
     */
    public String addManageAccount(ManageAccount manageAccount);
}
