package com.sipndy.product.service;

import com.sipndy.product.entity.Product;

import java.util.List;

public interface ProductService {

    public void addProduct(Product product);

    public void addProducts(List<Product> product);

    public Product getProduct(Long productId) throws Exception;

    public List<String> getProducts(String productCategory) throws Exception;

    public boolean deleteAllProduct(String username) throws Exception;
}
