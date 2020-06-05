package com.longi.dap.service.impl;


import com.longi.dap.dao.UserMapper;
import com.longi.dap.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 配置用户认证逻辑
     * @params 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(s);
        //据用户名，查找到对应的密码与权限
        List<UserInfo> users = userMapper.getUserDetails(userInfo);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("User not found for name:" + s);
        } else {
            for (UserInfo user : users) {
                userInfo.setPassword(user.getPassword());
                String roleCode = user.getRoleCode();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleCode);
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new User(userInfo.getUserName(),userInfo.getPassword(), grantedAuthorities);
    }
}
