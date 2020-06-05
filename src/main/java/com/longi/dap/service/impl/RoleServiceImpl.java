package com.longi.dap.service.impl;

import com.longi.dap.dao.RoleMapper;
import com.longi.dap.entity.RoleBean;
import com.longi.dap.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Map<String, String> addRole(RoleBean roleBean) {
        Map<String, String> resultMap = new HashMap<>();
        try{
            roleMapper.addRole(roleBean);
            resultMap.put("success", "true");
            resultMap.put("message","新增角色成功");
        } catch (Exception e) {
            resultMap.put("success", "false");
            resultMap.put("message","新增角色失败"+e.getMessage());
        }
        return resultMap;
    }

    @Override
    public List<RoleBean> getRoles() {
        return roleMapper.getRoles();
    }


    @Override
    public Map<String, String> deleteRole(RoleBean roleBean) {
        Map<String, String> resultMap = new HashMap<>();
        try{
            roleMapper.deleteRole(roleBean);
            resultMap.put("success", "true");
            resultMap.put("message","删除角色成功");
        } catch (Exception e) {
            resultMap.put("success", "false");
            resultMap.put("message","删除角色失败"+e.getMessage());
        }
        return resultMap;    }
}
