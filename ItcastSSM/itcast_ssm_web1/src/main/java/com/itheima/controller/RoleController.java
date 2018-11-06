package com.itheima.controller;


import com.itheima.domain.Permission;
import com.itheima.domain.PermissionToRole;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(/*Model model*/){
        System.out.println("查询所有商品");
        List<Role> list = roleService.findAll();
//        model.addAttribute("roleList",list);

        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList",list);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        System.out.println(role);
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    //给角色添加权限
    //1.先查询该用户 和 所有 权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(String id, Model model){
        Role role = roleService.findById(id);
        System.out.println(role);
        model.addAttribute("role",role);

        List<Permission> all = permissionService.findAll();
        model.addAttribute("permissionList",all);

        return "role-permission-add";
    }
    //将选中的权限加给该用户
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId, @RequestParam("ids")List<String> ids){

        PermissionToRole pr = new PermissionToRole();
        //遍历ids
        for (String permissionid : ids) {

            System.out.println("permissionid" +permissionid+"  roleId:"+roleId);
            pr.setPermissionid(permissionid);
            pr.setRoleid(roleId);
            System.out.println("插入");
            roleService.addPermissionToRole(pr);
        }

        return "redirect:/role/findAll";
    }
    //角色详情
    @RequestMapping("/findById")
    public String findById(String id,Model model){
        Role role = roleService.findById(id);
        System.out.println("role:---------" + role );
        model.addAttribute("role",role);
        return "role-show";
    }

    //角色删除
    @RequestMapping("/delete")
    public String delete(String id){
        roleService.delete(id);
        return "redirect:/role/findAll";
    }
}
