package com.longi.dap.entity;

import lombok.Data;

/**
 * 用户实体类对象
 * add by sjn
 */
@Data
public class UserBean {
    /**
     * 主键id
     **/
    private int id;

    /**
     * 唯一标识用户ID
     **/
    private String userId;
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
     * 真实名称
     */
     private String realName;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别 男1女0
     */
    private String sex;

    /**
     * 是否删除 1-未删除 0-删除
     */
    private String activeFlag;
}
