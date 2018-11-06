package com.itheima.controller;

import com.itheima.domain.Traveller;
import com.itheima.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/traveller")
public class TravellerController {

    @Autowired
    private TravellerService travellerService;
    
    @RequestMapping("/update")
    public String update(Traveller traveller){

        return "";
    }
}
