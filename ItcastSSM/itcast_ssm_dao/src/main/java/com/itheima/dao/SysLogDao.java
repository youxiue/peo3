package com.itheima.dao;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogDao {

    void save(SysLog sysLog);

    List<SysLog> findAll();


}
