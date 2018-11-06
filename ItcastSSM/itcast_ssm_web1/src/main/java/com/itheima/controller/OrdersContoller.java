package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersContoller {

    @Autowired
    private OrdersService ordersService;

//    @RequestMapping("/findAll")
//    public String findAll(Model model){
//        List<Orders> list  = ordersService.findAll();
//        System.out.println(list);
//        model.addAttribute("ordersList",list);
//        return "orders-list";
//    }

    //分页查询
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "3")Integer pageSize){

        ModelAndView mv = new ModelAndView();
        List<Orders> list  = ordersService.findAll(page,pageSize);
        PageInfo info = new PageInfo(list);
        mv.addObject("pageInfo",info);
        mv.setViewName("orders-page-list");
        System.out.println(list);
        return mv;
    }
    //订单详情页
    @RequestMapping("/findById")
    public String findById(String id,Model model){
        Orders orders = ordersService.findById(id);
        System.out.println("orders:-------"+orders);
        model.addAttribute("orders",orders);
        return "orders-show";
    }

    //订单修改  信息回显
    @RequestMapping("/findByIdUpdate")
    public String findByIdUpdate(String id,Model model){
        Orders orders = ordersService.findById(id);
        System.out.println("orders:-------"+orders);
        model.addAttribute("orders",orders);
        return "orders-update";
    }

    //订单详情修改
    @RequestMapping("/update")
    public String update(Orders orders,Model model){
        System.out.println("修改页面");
        System.out.println(orders);
        ordersService.update(orders);
        return "redirect:/orders/findAll";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(String[] ids){
        for (String id : ids) {
            if(id!=null && id.length()>0){
                ordersService.delete(id);
            }
        }
        return "redirect:/orders/findAll";
    }
}
