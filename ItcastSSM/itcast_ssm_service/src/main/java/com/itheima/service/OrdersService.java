package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll(int page,int pageSize);

    Orders findById(String id);

    void update(Orders orders);

    void delete(String id);
}
