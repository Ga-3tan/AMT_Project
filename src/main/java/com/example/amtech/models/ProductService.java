package com.example.amtech.models;

import com.example.amtech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    // CRUD operations

    //CREATE
    public void createGroceryItems() {
        System.out.println("Data creation started...");

        productRepo.save(new Product("1", "/img.png", "prod1", "Try to create product 1", 5.5, 10, false, 0, new String[]{"high-tech", "cpu"}));
        productRepo.save(new Product("2", "/img.png", "prod2", "Try to create product 1", 2.5, 1, false, 0, new String[]{"cpu", "lol"}));
        productRepo.save(new Product("3", "/img.png", "prod3", "Try to create product 1", 5, 4, true, 0.5, new String[]{"NaN"}));

        System.out.println("Data creation complete...");
    }

    // READ
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getById(String id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepo.allFromCategory(category);
    }

    public long count() {
        return productRepo.count();
    }

    //UPDATE

    //DELETE
    public void deleteAllProduct() {
        productRepo.deleteAll(); // Doesn't delete the collection
    }
}
