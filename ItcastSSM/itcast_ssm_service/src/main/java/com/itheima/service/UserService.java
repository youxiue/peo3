package com.itheima.service;

import com.itheima.domain.RoleToUser;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{


    List<UserInfo> findAll();

    UserInfo findByUsername(String username);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    void delete(String id);

    void addRoleToUser(RoleToUser roleToUser);

    void updateNoPassword(UserInfo user);

    void updateHavePassword(UserInfo user);
}
