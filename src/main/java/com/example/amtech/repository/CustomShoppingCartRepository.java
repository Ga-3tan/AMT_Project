package com.example.amtech.repository;

import com.example.amtech.models.ShoppingCart;

/**
 * Custom repository implementing additional methods for data access layers
 * for shopping cart in database.
 */
public interface CustomShoppingCartRepository {
    boolean updateShoppingCart(ShoppingCart newCart);
}
