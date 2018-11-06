package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.RoleToUser;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //用户登录  拦截验证
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("你好周四");
        //根据用户名查找用户
        UserInfo userInfo = userDao.findByUsername(username);
        //获取该用户所有的权限
        List<Role> roles = userInfo.getRoles();

        System.out.println("用户验证操作"+userInfo);

        User user = new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    //查询所有用户
    public List<UserInfo> findAll() {
        List<UserInfo> list = userDao.findAll();
        return list ;
    }

    public UserInfo findByUsername(String username) {
        UserInfo userInfo = userDao.findByUsername(username);
        return userInfo;
    }

    //保存用户
    public void save(UserInfo userInfo) {
        userDao.save(userInfo);
    }

    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }

    public void delete(String id) {
        userDao.deleteUser_role(id);
        System.out.println("先删从表");
        userDao.delete(id);
        System.out.println("主表删除");
    }

    public void addRoleToUser(RoleToUser roleToUser) {
        userDao.addRoleToUser(roleToUser);
    }

    /**
     * 密码没有改变的修改
     * @param user
     */
    public void updateNoPassword(UserInfo user) {
        userDao.updateNoPassword(user);
    }

    /**
     * 密码改变了的修改
     * @param user
     */
    public void updateHavePassword(UserInfo user) {
        userDao.updateHavePassword(user);

    }


}
