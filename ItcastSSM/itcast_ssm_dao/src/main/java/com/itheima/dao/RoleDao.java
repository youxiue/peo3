package com.itheima.dao;

import com.itheima.domain.PermissionToRole;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findByUserId(String userid);

    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void addPermissionToRole(PermissionToRole pr);

    /**
     * 根据角色id 删除用户角色中间表
     * @param id
     */
    void deleteUsers_Role(String id);

    /**
     * 根据角色id 删除角色权限中间表
     * @param id
     */
    void deleteRole_Permission(String id);

    /**
     * 删除角色
     * @param id
     */
    void delete(String id);

    List<Role> findNotUserId(String id);
}
