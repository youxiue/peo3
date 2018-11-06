package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    void delete(String id);

    Product findOne(String id);

    void update(Product product);

    List<Product> serach(String productName);
}
