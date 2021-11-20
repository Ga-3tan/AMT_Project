package com.example.amtech.models;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class ShoppingCart {
    public static final String ATTR_NAME = "cart";
    private Map<Product, Integer> products = new HashMap<>();

    public void addToCart(Product p) {
        Integer qty = products.get(p);
        if (qty == null) qty = 0;
        products.put(p, ++qty);
    }

    public void remove(Product p) {
        products.remove(p);
    }

    public void emptyCart() {
        products.clear();
    }

    public double getTotal() {
        return getProducts()
                .keySet()
                .stream()
                .map(p -> p.getPrice() * getProducts().get(p))
                .reduce(0., Double::sum);
    }
}
