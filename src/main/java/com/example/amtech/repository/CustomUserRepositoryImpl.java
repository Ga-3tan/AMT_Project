package com.example.amtech.repository;

import com.example.amtech.models.User;
import com.example.amtech.models.ShoppingCart;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ShoppingCart getShoppingCartFromUserById(String id) {
        Query query = new Query(Criteria.where("id").is(id));


        return result != null;
    }
}
