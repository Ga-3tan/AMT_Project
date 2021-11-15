package com.example.amtech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
@ToString
public class User {

    public User(String id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.shoppingCart = new ShoppingCart();
    }

    @Id
    private final String id;
    private String firstname;
    private String lastname;
    private String email;
    private ShoppingCart shoppingCart;
}
