package com.example.amtech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String img;

    @NotBlank(message = "Name is required")
    private String name;
    private String description;

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @Min(value = 0, message = "Rating cannot be negative")
    private int rating;
    private boolean sale;

    @DecimalMin(value = "0.0", message = "Sale cannot be negative")
    private double salePercentage;

    private String[] category;
}
