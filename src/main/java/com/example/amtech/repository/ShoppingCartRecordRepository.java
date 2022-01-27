package com.example.amtech.repository;

import com.example.amtech.models.ShoppingCartRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repository implementing data access layers for shopping cart record in database.
 */
public interface ShoppingCartRecordRepository extends MongoRepository<ShoppingCartRecord, String> {
    @Query("{id:'?0'}")
    ShoppingCartRecord findShoppingCartRecordById(String id);
}
