package com.longi.dap.dao;

import com.longi.dap.entity.UserBean;
import com.longi.dap.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

  /**
   * 获取用户信息列表
   * @param userBean
   * @return
   */
  public  List<UserBean> getUserInfo (UserBean userBean);


  /**
   * 查询用户详细信息，包含角色；
   * @param userInfo
   * @return
   */
  public List<UserInfo> getUserDetails(UserInfo userInfo);

  public void updatePwd (UserInfo userInfo);

  /**
   * 新增用户
   * @param userInfo
   */
  public void addUser (UserInfo userInfo);

  /**
   * 用户分配角色
   * @param userInfos
   * @return
   */
  int batchAddUserRole(@Param("userInfos") List<UserInfo> userInfos);

  /**
   * 删除用户
   * @param userBean
   */
  public void deleteUser (UserBean userBean);

  /**
   * 根据姓名查询用户
   * @param userName
   * @return
   */
  public UserBean selectUserByName (@Param("userName") String userName);

  /**
   * 用户列表查询
   * @param searchWord
   * @return
   */
  public  List<UserBean> getUsers (@Param("searchWord")String searchWord);



}
