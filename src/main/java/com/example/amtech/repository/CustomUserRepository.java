package com.example.amtech.repository;

import com.example.amtech.models.Product;
import com.example.amtech.models.ShoppingCart;

import java.util.List;

public interface CustomUserRepository {
    ShoppingCart getShoppingCartFromUserById(String id);
}
