package com.example.amtech.repository;

import com.example.amtech.models.Product;

public interface CustomProductRepository {
    boolean updateProductQuantity(String id, int qty);
    boolean updateProductCategories(String id, String[] categories);
    boolean updateProduct(String id, Product other);
}
