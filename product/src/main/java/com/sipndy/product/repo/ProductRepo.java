package com.sipndy.product.repo;

import com.sipndy.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product,String> {
    public Optional<List<Product>> findByProductCategory(String productCategory);

    @Query(delete = true, value = "{ 'createdBy': ?0 }")
    public void deleteByCreatedBy(String createdBy);
}
