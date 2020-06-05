package com.longi.dap.service.impl;

import com.alibaba.fastjson.JSON;
import com.longi.dap.dao.RoleMapper;
import com.longi.dap.dao.UserMapper;
import com.longi.dap.entity.PasswordBean;
import com.longi.dap.entity.RoleBean;
import com.longi.dap.entity.UserBean;
import com.longi.dap.entity.UserInfo;
import com.longi.dap.service.ILoginService;
import com.longi.dap.tookits.Md5Util;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    private static final Logger logger =  LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public String validateUser(UserBean userBean) throws Exception{
        // 返回结果
        Map<String, Object> reault = new HashMap<>();

        if (StringUtils.isEmpty(userBean.getUserName())) {
            reault.put("success", false);
            reault.put("msg", "用户名不能为空");
            return JSON.toJSONString(reault);
        }

        if (StringUtils.isEmpty(userBean.getPassword())) {
            reault.put("success", false);
            reault.put("msg", "密码不能为空");
            return JSON.toJSONString(reault);
        }

        // 获取用户数据
        List<UserBean> userList = userMapper.getUserInfo(userBean);
        if (!CollectionUtils.isEmpty(userList)){
            UserBean user = userList.get(0);
            String encodePwd = Md5Util.md5(userBean.getPassword(),"dap");
            logger.info("加密后的密码===="+encodePwd);
            if (Md5Util.verify(userBean.getPassword(),"dap", user.getPassword())) {
                reault.put("success", true);
                reault.put("msg", "校验成功");
            } else {
                reault.put("success", false);
                reault.put("msg", "校验失败，密码不匹配！");
            }
        } else {
            reault.put("success", false);
            reault.put("msg", "该用户不存在！");
        }
        return JSON.toJSONString(reault);
    }


    @Override
    public UserInfo getUserInfoByName(String userName) {
        UserInfo userInfo = new UserInfo();
        UserBean userBean = userMapper.selectUserByName(userName);
        List<RoleBean> roleBeans = roleMapper.getUserRolesByName(userName);
        if (!CollectionUtils.isEmpty(roleBeans)) {
            userInfo.setRoleBeanList(roleBeans);
        }
        if (Objects.nonNull(userBean)) {
            BeanUtils.copyProperties(userBean,userInfo);
        }
        return userInfo;
    }

    @Override
    public Map<String, String> changePwd(PasswordBean passwordBean) {
        Map<String, String> resultMap = new HashMap<>();
        UserInfo userInfo = getUserInfoByName(passwordBean.getUserName());
        String newPwd = passwordBean.getNewPwd();
        try{
            userInfo.setPassword(new BCryptPasswordEncoder().encode(newPwd));
            userMapper.updatePwd(userInfo);
            resultMap.put("success", "true");
            resultMap.put("message", "修改密码成功");
        } catch (Exception e) {
            resultMap.put("success", "false");
            resultMap.put("message", "修改密码失败:"+e.getMessage());
        }
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, String> addUser(UserInfo userInfo) {
        Map<String, String> resultMap = new HashMap<>();

        try {
            userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
            userMapper.addUser(userInfo);
            int id = userInfo.getUserId();
            List<String> roleList = userInfo.getRoleList();
            if (!CollectionUtils.isEmpty(roleList)) {
                List<UserInfo> userInfos = new ArrayList<>();
                for (String role : roleList) {
                    UserInfo user = new UserInfo();
                    user.setRoleId(Integer.parseInt(role));
                    user.setUserId(id);
                    userInfos.add(user);
                }
                userMapper.batchAddUserRole(userInfos);
            }
            resultMap.put("success","true");
        } catch (Exception e) {
            resultMap.put("success","false");
            throw new RuntimeException();
        }
        return resultMap;
    }

    @Override
    public List<RoleBean> getRoles() {
        List<RoleBean> roleBeans = new ArrayList<>();
        roleBeans = roleMapper.getRoles();
        return roleBeans;
    }

    @Override
    public List<UserBean> getUsers(String searchWord) {
        List<UserBean> userBeanList = userMapper.getUsers(searchWord);
        return userBeanList;
    }

    @Override
    public Map<String, String> deleteUser(UserBean userBean) {
        Map<String, String> map = new HashMap<>();
        try {
            // 删除为0
            userBean.setActiveFlag("0");
            userMapper.deleteUser(userBean);
            map.put("success","true");
            map.put("message", "删除成功");
        } catch (Exception e) {
            map.put("success","false");
            map.put("message", "删除失败"+e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, String> selectUserByName(String userName) {

        Map<String, String> map = new HashMap<>();
        try {

          UserBean userBean= userMapper.selectUserByName(userName);
          if (Objects.nonNull(userBean)) {
              map.put("success","true");
              map.put("message", "存在该用户");
          } else {
              map.put("success","false");
              map.put("message", "不存在该用户");
          }

        } catch (Exception e) {
            map.put("success","false");
            map.put("message", "查询失败"+e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, String> updateUser(UserInfo userInfo) {
        Map<String, String> resultMap = new HashMap<>();

        try {
            UserBean userBean = userMapper.selectUserByName(userInfo.getUserName());
            int id = userBean.getId();
            // 新选择的角色
            List<String> roleList = userInfo.getRoleList();
            // 已经分配的角色
            List<RoleBean> roleBeanList = roleMapper.getUserRolesByName(userInfo.getUserName());
            List<Integer> hasRoles  = new ArrayList<>();

            for (RoleBean roleBean : roleBeanList) {
                hasRoles.add(roleBean.getUserRoleId());
            }

            roleMapper.deleteUserRole(hasRoles);

            if (!CollectionUtils.isEmpty(roleList)) {
                List<UserInfo> userInfos = new ArrayList<>();
                for (String role : roleList) {
                    UserInfo user = new UserInfo();
                    user.setRoleId(Integer.parseInt(role));
                    user.setUserId(id);
                    userInfos.add(user);
                }
                userMapper.batchAddUserRole(userInfos);
            }
            resultMap.put("success","true");
        } catch (Exception e) {
            resultMap.put("success","false");
            throw new RuntimeException();
        }
        return resultMap;
    }
}
