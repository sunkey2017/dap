package com.longi.dap.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordBean {
    /**
     * 旧密码
     */
    private String oldPwd;

    /**
     * 新密码
     */
    private String newPwd;


    /**
     * 用户名
     */
    private String userName;
}
