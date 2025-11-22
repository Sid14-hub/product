package com.sipndy.product.repo;

import com.sipndy.product.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,String> {
    public Optional<List<Product>> findByProductCategory(String productCategory);
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.createdBy = :createdBy")
    public void deleteByCreatedBy(@Param("createdBy") String createdBy);
}
