package com.longi.dap.controller;


import com.alibaba.fastjson.JSON;
import com.longi.dap.entity.*;
import com.longi.dap.service.ILoginService;
import com.longi.dap.tookits.CommonUtil;
import com.longi.dap.tookits.HttpClientUtil;
import com.longi.dap.tookits.WebResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 用户登录
 * add by Janey
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/validateUser",method = RequestMethod.POST)
    public WebResult validateUser(@RequestBody UserBean userBean) {

        try {
            String validateResult = loginService.validateUser(userBean);
            WebResult result = WebResultUtil.getResult(validateResult);
            return result;
        } catch (Exception e) {
            WebResult result = WebResultUtil.getResult(e.getMessage());
            return result;
        }
    }


    @RequestMapping(value="/getUserInfoByName")
    public WebResult getUserInfoByName(@RequestParam String userName){
        WebResult result = null;
        UserInfo userInfo = new UserInfo();
        try
        {
            userInfo = loginService.getUserInfoByName( userName);
            result = WebResultUtil.getResult(JSON.toJSONString(userInfo));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/changePwd")
    public WebResult changePwd(@RequestBody PasswordBean passwordBean){
        WebResult result = null;
        try
        {
            Map<String, String> map = loginService.changePwd(passwordBean);
            result = WebResultUtil.getResult(JSON.toJSONString(map));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/addUser")
    public WebResult addUser(@RequestBody UserInfo userInfo){
        WebResult result = null;
        try
        {
            Map<String, String> map = loginService.addUser(userInfo);
            result = WebResultUtil.getResult(JSON.toJSONString(map));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/getRoles")
    public WebResult getRoles(HttpServletRequest httpServletRequest){
        WebResult result = null;
        try
        {
           List<RoleBean> roles = loginService.getRoles( );
            result = WebResultUtil.getResult(JSON.toJSONString(roles));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/getUsers")
    public WebResult getUsers(@RequestParam String searchWord){
        WebResult result = null;
        try
        {
           List<UserBean> userInfos = loginService.getUsers( searchWord);
            result = WebResultUtil.getResult(JSON.toJSONString(userInfos));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/deleteUser")
    public WebResult deleteUser(@RequestBody UserBean userBean){
        WebResult result = null;
        try
        {
            Map<String, String> res = loginService.deleteUser( userBean);
            result = WebResultUtil.getResult(JSON.toJSONString(res));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }


    @RequestMapping(value="/selectUserByName")
    public WebResult selectUserByName(@RequestParam String userName){
        WebResult result = null;
        try
        {
            Map<String, String> map = loginService.selectUserByName( userName);
            result = WebResultUtil.getResult(JSON.toJSONString(map));

        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }

    @RequestMapping(value="/updateUser")
    public WebResult updateUser(@RequestBody UserInfo userInfo){
        WebResult result = null;
        try
        {
            Map<String, String> map = loginService.updateUser(userInfo);
            result = WebResultUtil.getResult(JSON.toJSONString(map));
        }catch (Exception e){
            result = WebResultUtil.getResult( null);
            CommonUtil.getExceptionDetail(e);
        }
        return result;
    }
}
