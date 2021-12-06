package com.example.amtech.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component

//DPE - C'est un model votre ShoppingCart ?
public class ShoppingCart {
    public static final String ATTR_NAME = "cart";
    private Map<Product, Integer> products = new HashMap<>();

    public void setProduct(Product p, int quantity) {
        // Removes product if qty = 0
        if (quantity <= 0) products.remove(p);
        else products.put(p, quantity);
    }

    public int getProductQuantity(Product p) {
        Integer qty = products.get(p);
        return qty == null ? 0 : qty;
    }

    public double getProductTotal(Product p) {
        return Math.round(getProductQuantity(p) * p.getPrice() * 100d) / 100d;
    }

    public void remove(Product p) {
        products.remove(p);
    }

    public void emptyCart() {
        products.clear();
    }

    public double getTotal() {
        return Math.round(getProducts()
                .keySet()
                .stream()
                .map(p -> p.getPrice() * getProducts().get(p))
                .reduce(0., Double::sum) * 100d) / 100d;
    }
}
