package com.example.amtech.models;

import com.example.amtech.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    // CRUD operations
    public void deleteAllProduct() {
        productRepo.deleteAll(); // Doesn't delete the collection
    }

    //CREATE
    public void createGroceryItems() {
        System.out.println("Data creation started...");

        productRepo.save(new Product("1", "/img.png", "prod1", "Try to create product 1", 5.5, 10, false, 0, new String[]{"high-tech", "cpu"}));
        productRepo.save(new Product("2", "/img.png", "prod2", "Try to create product 1", 2.5, 1, false, 0, new String[]{"gpu", "lol"}));
        productRepo.save(new Product("3", "/img.png", "prod3", "Try to create product 1", 5, 4, true, 0.5, new String[]{"NaN"}));

        System.out.println("Data creation complete...");
    }

    // READ
    List<Product> itemList = new ArrayList<>();
    // 1. Show all the data
    public void showAllProducts() {

        itemList = productRepo.findAll();

        itemList.forEach(item -> System.out.println(item.toString()));
    }
}
