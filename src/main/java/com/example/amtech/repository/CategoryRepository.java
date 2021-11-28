package com.example.amtech.repository;

import com.example.amtech.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query("{name:'?0'}")
    Optional<Category> findCategoryByName(String name);
}
