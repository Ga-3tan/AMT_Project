package com.example.amtech.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@Document
public class Product {
    @Id
    private final String id;
    @Field
    private String img;
    @Field
    private String name;
    @Field
    private String description;
//    @Field
//    private Object productDetails;
    @Field
    private double price;
    @Field
    private int quantity;
    @Field
    private boolean sale;
    @Field
    private double salePercentage;
    @Field
    private String[] category;
}
