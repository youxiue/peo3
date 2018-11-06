package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    public void save(SysLog sysLog) {
        System.out.println("保存方法");
        sysLogDao.save(sysLog);
    }

    public List<SysLog> findAll() {
        List<SysLog> list  = sysLogDao.findAll();
        return list ;
    }
}
