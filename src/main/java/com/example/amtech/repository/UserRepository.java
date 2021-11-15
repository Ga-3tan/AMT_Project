package com.example.amtech.repository;

import com.example.amtech.models.Product;
import com.example.amtech.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{id:'?0'}")
    User findUserById(String id);

    @Query("{email:'?0}")
    User findUserByEmail(String email);
}
