package com.example.amtech.repository;

import com.example.amtech.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    @Query("{id:'?0'}")
    ShoppingCart findShoppingCartById(String id);

    @Query("{userId:'?0'}")
    ShoppingCart findShoppingCartByUserId(String userId);
}
