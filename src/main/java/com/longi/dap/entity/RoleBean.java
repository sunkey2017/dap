package com.longi.dap.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleBean {

    /**
     * 主键id
     */
    private int id;
    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 用户角色表id
     */
    private int userRoleId;
}


