package com.itheima.controller;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.Role;
import com.itheima.domain.RoleToUser;
import com.itheima.domain.UserInfo;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    @RolesAllowed("USER")
    public String findAll(Model model){
        List<UserInfo> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "user-list";
    }

    @RequestMapping("/findByUsername")
    public @ResponseBody boolean findByUsername(String username, HttpServletResponse response) throws IOException {
        System.out.println(username);
        UserInfo user =  userService.findByUsername(username);
        System.out.println("用户："+user);
        boolean flag = false;
        if(user!=null){
            flag = true;
        }
        return flag;
    }

    @RequestMapping("/save")
    @RolesAllowed("USER")
    public String save(UserInfo userInfo){
        System.out.println(userInfo);
        String encode = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encode);

        System.out.println(encode);
        userService.save(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping("findById")
    @RolesAllowed("USER")
    public String findById(String id,Model model){
        UserInfo userInfo = userService.findById(id);
        model.addAttribute("user",userInfo);
        return "user-show";
    }

    @RequestMapping("/delete")
    public String delete(String[] arr){
        for (String id : arr) {
            if(id!=null && id.length()>0){
                System.out.println(id);
                userService.delete(id);
            }
        }
        return "redirect:/user/findAll";
    }

    //查询 当前用户 及所有角色  用于给该用户添加角色
    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(String id,Model model){
        UserInfo user = userService.findById(id);
        model.addAttribute("user",user);

//        List<Role> roleList = roleService.findAll();
        //应该是查询该用户所没有的角色
        List<Role> roleList = roleService.findNotUserId(id);

        model.addAttribute("roleList",roleList);
        return "user-role-add";
    }

    //给该用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,@RequestParam("ids") List<String> ids){
//        表单提交的信息有${user.id}  ${role.id}
        System.out.println("用户id:"+userId);
        System.out.println("角色id:"+ids.toString());
        RoleToUser roleToUser = new RoleToUser();
        for (String id : ids) {
            roleToUser.setUserid(userId);
            roleToUser.setRoleid(id);
            userService.addRoleToUser(roleToUser);
        }
        return "redirect:/user/findAll";
    }

    //修改用户时查询该账户  用于信息的回显
    @RequestMapping("/findByIdUpdate")
    public String findByIdUpdate(String id,Model model){
        UserInfo userInfo = userService.findById(id);
        model.addAttribute("user",userInfo);
        return "user-update";
    }
    @RequestMapping("/update")
    public String update(UserInfo user){
        //先查询该用户  判断密码是否改变  调用不同的方法修改
        System.out.println(user);
        UserInfo byId = userService.findById(user.getId());
        if(user.getPassword().equals(byId.getPassword())){
            //密码没有改变时的修改
            System.out.println("密码没有改变");
            userService.updateNoPassword(user);
        }else{
            //密码发生改变的修改
            System.out.println("密码改变了");
            System.out.println("原密码"+byId.getPassword());
            System.out.println("新密码"+user.getPassword());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.updateHavePassword(user);
        }
        return "redirect:/user/findAll";
    }
}
