package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.PermissionToRole;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    public List<Role> findAll() {
        List<Role> list = roleDao.findAll();
        return list;
    }

    public void save(Role role) {
        roleDao.save(role);
    }

    public Role findById(String id) {
        Role role = roleDao.findById(id);
        return role;
    }

    public void addPermissionToRole(PermissionToRole pr) {
        roleDao.addPermissionToRole(pr);
    }

    public void delete(String id) {
        roleDao.deleteUsers_Role(id);
        roleDao.deleteRole_Permission(id);
        roleDao.delete(id);
    }

    public List<Role> findNotUserId(String id) {
        List<Role> list = roleDao.findNotUserId(id);

        return list;
    }
}
