package com.longi.dap.service;


import com.longi.dap.entity.RoleBean;

import java.util.List;
import java.util.Map;

/**
 * 角色服务层
 * add by Janey
 */
public interface IRoleService {

    /**
     * 新增角色
     * @param roleBean
     * @return
     */
    Map<String,String> addRole (RoleBean roleBean);

    /**
     * 获取角色列表
     * @return
     */
    List<RoleBean> getRoles ();

    /**
     * 删除角色
     */
    Map<String,String> deleteRole(RoleBean roleBean);


}
