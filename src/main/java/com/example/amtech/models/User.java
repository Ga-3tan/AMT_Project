package com.example.amtech.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class User {

    public User(String id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.shoppingCart = new ShoppingCart();
    }

    @Id
    private String id;
    private String username;
    private String role;
    private ShoppingCart shoppingCart;
}
