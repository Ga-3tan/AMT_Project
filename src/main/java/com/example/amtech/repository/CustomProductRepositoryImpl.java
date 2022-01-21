package com.example.amtech.repository;

import com.example.amtech.models.Product;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean updateProductQuantity(String id, int newQuantity) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("quantity", newQuantity);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Product.class);

        return result.wasAcknowledged();
    }

    @Override
    public boolean updateProductCategories(String id, String[] newCategories) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("category", newCategories);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Product.class);

        return result.wasAcknowledged();
    }

    @Override
    public boolean updateProduct(String id, Product other) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("img", other.getImg());
        update.set("name", other.getName());
        update.set("description", other.getDescription());
        update.set("price", other.getPrice());
        update.set("quantity", other.getQuantity());
        update.set("rating", other.getRating());
        update.set("salePercentage", other.getSalePercentage());
        update.set("category", other.getCategory());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Product.class);

        return result.wasAcknowledged();
    }
}
