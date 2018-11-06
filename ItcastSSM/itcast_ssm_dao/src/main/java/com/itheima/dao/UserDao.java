package com.itheima.dao;

import com.itheima.domain.RoleToUser;
import com.itheima.domain.UserInfo;

import java.util.List;

public interface UserDao {

    UserInfo findByUsername(String username);

    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    void delete(String id);

    void deleteUser_role(String userid);

    void addRoleToUser(RoleToUser roleToUser);

    void updateNoPassword(UserInfo user);

    void updateHavePassword(UserInfo user);

}
