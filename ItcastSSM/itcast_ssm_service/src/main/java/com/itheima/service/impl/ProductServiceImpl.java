package com.itheima.service.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> findAll() {
        List<Product> list = productDao.findAll();
        return list;
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public void delete(String id) {
        productDao.delete(id);
    }

    public Product findOne(String id) {
        Product product = productDao.findOne(id);
        return product;
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public List<Product> serach(String productName) {
        productName = "%"+productName+"%";
        List<Product> list = productDao.search(productName);
        return list;
    }
}
