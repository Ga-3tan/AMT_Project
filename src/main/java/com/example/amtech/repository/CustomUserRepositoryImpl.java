package com.example.amtech.repository;

import com.example.amtech.models.User;
import com.example.amtech.models.ShoppingCart;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CustomUserRepositoryImpl implements CustomUserRepository {

    MongoTemplate mongoTemplate;

    @Override
    public ShoppingCart getShoppingCartFromUserById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        query.fields().include("shoppingCart");
        User user = mongoTemplate.findOne(query, User.class);
        return user.getShoppingCart();
    }
}
