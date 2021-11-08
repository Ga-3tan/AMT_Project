package com.example.amtech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private final String id;
    private String img;
    private String name;
    private String description;
//    private Object productDetails;
    private double price;
    private int quantity;
    private int rating;
    private boolean sale;
    private double salePercentage;
    private String[] category;
}
