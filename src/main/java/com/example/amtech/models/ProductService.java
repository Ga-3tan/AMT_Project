package com.example.amtech.models;

import com.example.amtech.repository.CustomProductRepository;
import com.example.amtech.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepo;

    private CustomProductRepository customProductRepo;

    // CRUD operations

    //CREATE
    public String createProduct(String id, String img, String name, String description, double price, int quantity, int rating, boolean sale, double salePercentage, String[] category) {
        Product p = productRepo.save(new Product(id, img, name, description, price, quantity, rating, sale, salePercentage, category));
        return p.getId();
    }
    public String createProduct(Product product) {
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

    public Product getByName(String name) {
        return productRepo.findProductByName(name).orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepo.allProductsFromCategory(category);
    }

    public long count() {
        return productRepo.count();
    }

    //UPDATE
    public boolean updateProductQuantity(String id, int newQuantity) {
        return customProductRepo.updateProductQuantity(id, newQuantity);
    }

    public boolean updateProductCategories(String id, String[] newCategories) {
        return customProductRepo.updateProductCategories(id, newCategories);
    }

    public boolean updateProduct(String id, Product other) {
        return customProductRepo.updateProduct(id, other);
    }

    //DELETE
    public void deleteAllProduct() {
        productRepo.deleteAll(); // Doesn't delete the collection
    }

    public void deleteById(String id) {
        productRepo.deleteById(id);
    }
}
