package com.itheima.service.impl;

import com.itheima.dao.EmpDao;
import com.itheima.domain.Emp;
import com.itheima.domain.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empService")
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;


    public List<Emp> findAllEmp() {
        List<Emp> empList = empDao.findAllEmp();
        return empList;
    }

    public PageBean<Emp> pageQuery(PageBean<Emp> pageBean) {
        //pageBean  能拿到当前页  每页多少条信息
        Integer currentPage = pageBean.getCurrentPage();
        Integer rows = pageBean.getRows();
        //调用底层查询总共 多少条信息
        int totalCount = empDao.findTotalCount();
        //求出总共多少页
        int totalPage = (int) Math.ceil(totalCount * 1.0 / rows);
        //求出起始索引
        int start = 1;
        //求 结束索引
        int end = 5;
        //查询结果集
        return null;
    }
}
