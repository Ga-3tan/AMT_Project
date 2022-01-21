package com.example.amtech.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * Domain object model class representing a shopping cart record.
 */
@Data
@Component
@NoArgsConstructor
@Document(collection = "shoppingCartRecords")
public class ShoppingCartRecord {
    @Id
    String id;
    public Product product;
    public Integer quantity;

    ShoppingCartRecord(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
