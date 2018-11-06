package com.itheima.dao;

import com.itheima.domain.Emp;

import java.util.List;

public interface EmpDao {
    List<Emp> findAllEmp();

    int findTotalCount();

}
