package com.itheima.dao;

import com.itheima.domain.Traveller;

import java.util.List;

public interface TravellerDao {

    Traveller findByOrdersId(String id);
}
