package com.example.amtech.models;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addToCart(Product p) {
        products.add(p);
    }
}
