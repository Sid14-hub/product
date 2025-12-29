package com.sipndy.product.repo;

import com.sipndy.product.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepo extends MongoRepository<Cart, String> {

    public List<Cart> findByAddedBy(String username);

}
