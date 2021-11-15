package com.example.amtech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "users")
@ToString
public class User {
    @Id
    private final String id;
    private String firstname;
    private String lastname;
    private String email;
    private ShoppingCart shoppingCart;
}
