package com.itheima.service;

import com.itheima.domain.PermissionToRole;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);


    Role findById(String id);

    void addPermissionToRole(PermissionToRole pr);

    void delete(String id);

    List<Role> findNotUserId(String id);
}
