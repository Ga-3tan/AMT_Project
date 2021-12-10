package com.example.amtech.repository;

import com.example.amtech.models.ShoppingCart;

public interface CustomUserRepository {
    ShoppingCart getShoppingCartFromUserById(String id);
}
