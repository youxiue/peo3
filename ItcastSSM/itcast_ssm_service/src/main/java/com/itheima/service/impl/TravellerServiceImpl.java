package com.itheima.service.impl;

import com.itheima.dao.TravellerDao;
import com.itheima.domain.Traveller;
import com.itheima.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("travellerService")
public class TravellerServiceImpl implements TravellerService {

    @Autowired
    private TravellerDao travellerDao;

//    public List<Traveller> findByOrdersId(String id){
//        List<Traveller> list = travellerDao.findByOrdersId(id);
//        return list;
//    }
}
