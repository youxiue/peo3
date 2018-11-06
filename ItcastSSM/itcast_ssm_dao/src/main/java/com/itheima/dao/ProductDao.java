package com.itheima.dao;

import com.itheima.domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void save(Product product);


    void delete(String id);

    Product findOne(String id);

    void update(Product product);

    List<Product> search(String productName);
}
