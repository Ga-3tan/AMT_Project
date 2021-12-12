package com.example.amtech.repository;

import com.example.amtech.models.Product;
import com.example.amtech.models.ShoppingCart;

public interface CustomShoppingCartRespository {
    boolean updateShoppingCart(ShoppingCart newCart);
}
