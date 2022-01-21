package com.example.amtech.repository;

import com.example.amtech.models.Product;

/**
 * Custom repository implementing additional methods for data access layers
 * for products in database.
 */
public interface CustomProductRepository {
    boolean updateProductQuantity(String id, int qty);
    boolean updateProductCategories(String id, String[] categories);
    boolean updateProduct(String id, Product other);
}
