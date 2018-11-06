package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Permission> list = permissionService.findAll();
        System.out.println(list);
        model.addAttribute("permissionList",list);
        return "permission-list";
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);


        return "redirect:/permission/findAll";
    }
}
