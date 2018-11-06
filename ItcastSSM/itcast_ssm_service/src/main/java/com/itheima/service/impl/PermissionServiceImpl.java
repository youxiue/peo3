package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("/permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> findAll() {
        List<Permission> list = permissionDao.findAll();
        return list;
    }

    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
