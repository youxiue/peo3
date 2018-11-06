package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.StringToDateDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 订单所有查询
     * @return
     */
    @RequestMapping("/findAll")
//    @RolesAllowed("USER")
//    @Secured("ROLE_USER")
    @PreAuthorize("authentication.principal.username=='youxiue'")
    public String findAll(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "product-list";
    }

    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='youxiue'")
    public String save(Product product){
        String str = product.getDepartureTimeStr();
        Date date = new StringToDateDemo().convert(str);
        product.setDepartureTime(date);
        productService.save(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/delete")
    @PreAuthorize("authentication.principal.username=='youxiue'")
    public String delete(@RequestParam List<String> ids){
        System.out.println("你好:"+ids);
        for (String id : ids) {
            productService.delete(id);
        }
        return "redirect:/product/findAll";
    }

    @RequestMapping("/update")
    @PreAuthorize("authentication.principal.username=='youxiue'")
    public String update(Product product){
        System.out.println("你好棒---"+product);
        String str = product.getDepartureTimeStr();
        Date date = new StringToDateDemo().convert(str);
        product.setDepartureTime(date);
        productService.update(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/findOne")
    @PreAuthorize("authentication.principal.username=='youxiue'")
    public String findOne(String id,Model model){
        Product product = productService.findOne(id);
        model.addAttribute("product",product);
        return "product-update";
    }
    @RequestMapping("/search")
    public String search(String productName,Model model){
        System.out.println(productName);
        List<Product> list = productService.serach(productName);
        System.out.println(list);
        model.addAttribute("productList",list);
        return "product-list";
    }
}
