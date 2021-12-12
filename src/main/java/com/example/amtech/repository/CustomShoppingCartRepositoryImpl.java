package com.example.amtech.repository;

import com.example.amtech.models.Product;
import com.example.amtech.models.ShoppingCart;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.bson.BSONObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class CustomShoppingCartRepositoryImpl implements CustomShoppingCartRespository{

    private MongoTemplate mongoTemplate;

    @Override
    public boolean updateShoppingCart(ShoppingCart newCart) {
        Query query = new Query(Criteria.where("id").is(newCart.getId()));
        Update update = new Update();

        // Sets the products
        update.set("products", new BasicDBObject(newCart.getProducts()));
        UpdateResult result = mongoTemplate.updateFirst(query, update, ShoppingCart.class);

        return result.wasAcknowledged();
    }
}
