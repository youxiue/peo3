package com.itheima.controller;

import com.itheima.domain.Emp;
import com.itheima.domain.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/findAllEmp")
    public String findAllEmp(Model model){
        List<Emp> list = empService.findAllEmp();
        model.addAttribute("empList",list);
        System.out.println(list);
        return "emplist";
    }

    @RequestMapping("/pageQuery")
    public String pageQuery(PageBean<Emp> pageBean,Model model){

        //传递到service进行处理
        pageBean = empService.pageQuery(pageBean);
        //存到域中
        model.addAttribute("pb",pageBean);
        return null;
    }

}
