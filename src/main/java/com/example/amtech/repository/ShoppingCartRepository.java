package com.example.amtech.repository;

import com.example.amtech.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repository implementing data access layers for shopping cart in database.
 */
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    @Query("{userId:'?0'}")
    ShoppingCart findShoppingCartByUserId(String userId);
}
