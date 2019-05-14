package com.jinqshen.weixin.mapper;

import com.jinqshen.weixin.pojo.table.ManageAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理账户持久层
 */
@Repository
public interface ManageMapper {

    /**
     * 查询管理账户是否存在
     * @param manage_username 管理用户名
     * @param manage_password 管理用户密码
     * @return
     */
    public ManageAccount findTheManageAccount(@Param("manage_username") String manage_username,@Param("manage_password") String manage_password);

    /**
     * 通过用户名查询管理账户
     * @param manage_username
     * @return
     */
    public ManageAccount findTheManageAccountByUserName(@Param("manage_username") String manage_username);

    /**
     * 更新用户密码
     * @param manage_username
     * @param manage_password
     */
    public void updateManagePassword(@Param("manage_username") String manage_username,@Param("manage_password") String manage_password);

    /**
     * 新增管理员
     * @param manage_username
     * @param manage_password
     */
    public void insertManageAccount(@Param("manage_username") String manage_username,@Param("manage_password") String manage_password);
}
