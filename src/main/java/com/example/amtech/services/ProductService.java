package com.example.amtech.services;

import com.example.amtech.models.Product;
import com.example.amtech.repository.CustomProductRepository;
import com.example.amtech.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Service offering products management.
 * It provides principal methods for CRUD operations on products.
 */
@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepo;
    private CustomProductRepository customProductRepo;

    //CREATE
    public String createProduct(String id, String img, String name, String description, double price, int quantity, int rating, boolean sale, double salePercentage, String[] category) {
        Product p = productRepo.save(new Product(id, img, name, description, price, quantity, rating, sale, salePercentage, category));
        return p.getId();
    }
    public String createProduct(Product product) throws Exception {
        if (existsByName(product.getName())) {
            throw new Exception("Product already exists");
        }
        Product p = productRepo.save(product);
        return p.getId();
    }

    // READ
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getById(String id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepo.allProductsFromCategory(category);
    }

    public boolean existsByName(String name) {
        return productRepo.findProductByName(name).orElse(null) != null;
    }

    //UPDATE
    public boolean updateProductQuantity(String id, int newQuantity) {
        return customProductRepo.updateProductQuantity(id, newQuantity);
    }

    public void updateProductCategories(String id, String[] newCategories) {
        customProductRepo.updateProductCategories(id, newCategories);
    }

    public void removeCategoryFromProduct(String categoryName) {
        List<Product> products = getProductsByCategory(categoryName);
        for (Product p : products) {
            String[] cat = Arrays.stream(p.getCategory()).filter(s -> !s.equals(categoryName)).toArray(String[]::new);
            updateProductCategories(p.getId(), cat);
        }
    }

    public void updateProduct(String id, Product other) {
        customProductRepo.updateProduct(id, other);
    }

    //DELETE
    public void deleteAllProduct() {
        productRepo.deleteAll(); // Doesn't delete the collection
    }

    public void deleteById(String id) {
        productRepo.deleteById(id);
    }
}
