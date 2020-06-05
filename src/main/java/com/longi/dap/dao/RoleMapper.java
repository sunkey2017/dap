package com.longi.dap.dao;


import com.longi.dap.entity.RoleBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoleMapper {

  /**
   * 获取角色信息
   * @return
   */
  public List<RoleBean> getRoles();

  /**
   * 查询用户分配的角色
   * @param userName 用户名
   * @return
   */
  public List<RoleBean> getUserRolesByName (@Param("userName") String userName);

  /**
   * 删除用户角色关系
   * @param list
   */
  public void deleteUserRole(@Param("list") List<Integer> list);

  /**
   * 新增角色
   * @param roleBean
   */
  public void addRole(RoleBean roleBean);

  /**
   * 删除角色
   * @param roleBean
   */
  public void deleteRole(RoleBean roleBean);
}
