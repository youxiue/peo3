package com.itheima.dao;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionDao {

    List<Permission> findByRoleId(String roleid);

    List<Permission> findAll();

    void save(Permission permission);

}
