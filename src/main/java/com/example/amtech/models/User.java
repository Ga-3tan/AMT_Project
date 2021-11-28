package com.example.amtech.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@ToString
public class User {

    public User(String id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.shoppingCart = new ShoppingCart();
    }

    @Id
    private final String id;
    private String username;
    private String role;
    private ShoppingCart shoppingCart;
}
