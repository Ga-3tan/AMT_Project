package com.example.amtech.repository;

public interface CustomProductRepository {
    boolean updateProductQuantity(String id, int qty);
}
