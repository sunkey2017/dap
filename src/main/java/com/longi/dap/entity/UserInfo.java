package com.longi.dap.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户信息bean 包含基本用户信息和角色信息
 */
@Setter
@Getter
public class UserInfo {

    /**
     * 唯一标识用户ID
     **/
    private int userId;
    /**
     * 用户名称
     **/
    private String userName;
    /**
     * 用户密码
     **/
    private String password;
    /**
     * 电话
     **/
    private String mobile;
    /**
     * 邮箱
     **/
    private String email;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String description;


    /**
     * 真实名称
     */
    private String realName;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 角色列表
     */
    private List<String> roleList;

    /**
     * 角色id
     */
    private int roleId;

    /**
     * 角色详细列表
     */
    private List<RoleBean> roleBeanList;

}
