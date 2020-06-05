package com.longi.dap.service;


import com.longi.dap.entity.PasswordBean;
import com.longi.dap.entity.RoleBean;
import com.longi.dap.entity.UserBean;
import com.longi.dap.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户登录服务层
 * add by Janey
 */
public interface ILoginService {

String validateUser (UserBean userBean) throws Exception;

    /**
     * 通过名称查询用户信息
     * @param userName
     * @return
     */
    UserInfo getUserInfoByName (String userName);

    /**
     * 修改密码
     * @param passwordBean
     * @return
     */
    Map<String, String> changePwd (PasswordBean passwordBean);


    /**
     * 新增用户
     */
    Map<String, String> addUser (UserInfo userInfo);

    /**
     * 获取角色信息
     * @return
     */
    List<RoleBean> getRoles();

    /**
     * 获取用户信息列表
     * @param searchWord
     * @return
     */
    List<UserBean> getUsers(String searchWord);

    /**
     * 删除用户
     * @param userBean
     * @return
     */
    Map<String, String> deleteUser (UserBean userBean);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    Map<String, String> selectUserByName (String userName);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    Map<String, String> updateUser (UserInfo userInfo);

}
