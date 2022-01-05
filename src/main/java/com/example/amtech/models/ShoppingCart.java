package com.example.amtech.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@Component
@Document(collection = "shoppingCarts")
public class ShoppingCart {
    public static final String ATTR_NAME = "cart";

    @Id
    String id;
    String userId;

    private Map<String, ShoppingCartRecord> products = new HashMap<>();

    public void setProduct(Product p, int quantity) {
        // Removes product if qty = 0
        if (quantity <= 0) products.remove(p);
        else products.put(p.getId(), new ShoppingCartRecord(p, quantity));
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getProductQuantity(Product p) {
        if (!products.containsKey(p.getId())) return 0;
        Integer qty = products.get(p.getId()).quantity;
        return qty == null ? 0 : qty;
    }

    public double getProductTotal(Product p) {
        return Math.round(getProductQuantity(p) * p.getPrice() * 100d) / 100d;
    }

    public void remove(Product p) {
        products.remove(p.getId());
    }

    public void emptyCart() {
        products.clear();
    }

    public double getTotal() {
        return Math.round(getProducts()
                .values()
                .stream()
                .map(r -> r.product.getPrice() * r.quantity)
                .reduce(0., Double::sum) * 100d) / 100d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return getId().equals(that.getId()) && Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducts());
    }

    public void setAll(ShoppingCart cart) {
        this.setId(cart.getId());
        this.setUserId(cart.getUserId());
        this.setProducts(cart.getProducts());
    }
}
