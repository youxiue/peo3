package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.dao.Orders_TravellerDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private Orders_TravellerDao orders_travellerDao;


    public List<Orders> findAll(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Orders> list = ordersDao.findAll();
        return list;
    }

    public Orders findById(String id) {
        Orders orders = ordersDao.findById(id);
        System.out.println(orders);
        return orders;
    }

    public void update(Orders orders) {
        ordersDao.update(orders);
    }

    public void delete(String id) {
        orders_travellerDao.delete(id);
        System.out.println("从表删除成功");
        ordersDao.delete(id);
        System.out.println("主表删除成功");
    }
}
