package com.itheima.service;

import com.itheima.domain.Emp;
import com.itheima.domain.PageBean;

import java.util.List;

public interface EmpService {
    List<Emp> findAllEmp();

    PageBean<Emp> pageQuery(PageBean<Emp> pageBean);

}
