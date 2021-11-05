package com.example.amtech.repository;

import com.example.amtech.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{name:'?0'}")
    Product findProductByName(String name);

    @Query(value="{category:'?0'}")
    List<Product> allFromCategory(String category);
}
