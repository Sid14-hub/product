package com.sipndy.product.repo;

import com.sipndy.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,String> {
    public Optional<List<Product>> findByProductCategory(String productCategory);
}
