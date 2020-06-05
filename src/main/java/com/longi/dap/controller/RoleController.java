package com.longi.dap.controller;

import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.RoleBean;
import com.longi.dap.entity.UserBean;
import com.longi.dap.entity.WebResult;
import com.longi.dap.service.IRoleService;
import com.longi.dap.tookits.CommonUtil;
import com.longi.dap.tookits.WebResultUtil;
import com.netflix.client.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * add by Janey
 */
@RestController
@RequestMapping("/role")
public class RoleController {

   @Autowired
   private IRoleService roleService;

    @RequestMapping(value="/addRole")
    public WebResult addRole(@RequestBody RoleBean roleBean){
        WebResult result = null;
        try
        {
            Map<String, String> map = roleService.addRole(roleBean);
            result = WebResultUtil.getResult(JSON.toJSONString(map));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/getRoleList")
    public WebResult getRoleList(HttpRequest httpRequest){
        WebResult result = null;
        try
        {
            List<RoleBean> list = roleService.getRoles();
            result = WebResultUtil.getResult(JSON.toJSONString(list));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/deleteRole")
    public WebResult deleteRole(@RequestBody RoleBean roleBean){
        WebResult result = null;
        try
        {
            Map<String, String> map = roleService.deleteRole(roleBean);
            result = WebResultUtil.getResult(JSON.toJSONString(map));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }
}
