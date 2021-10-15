package com.example.amtech.controllers;

import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/amtech")
public class DBController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to AMTech";
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        log.info("returning the product with id : " + id);
        return productService.getProduct(id).orElse(null);
    }

    // TODO: fix empty list
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        log.info("returning all products from /all");
        return productService.getProducts();
    }

}
