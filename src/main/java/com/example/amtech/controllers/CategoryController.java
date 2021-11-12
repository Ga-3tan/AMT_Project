package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import com.example.amtech.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CategoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/category")
    public String shop(Model model) {
        List<Product> products = this.productService.getAllProducts();

        model.addAttribute("products", products);
        model.addAttribute("categoryName", "All categories");

        return "shop";
    }

    @GetMapping("/category/{categoryName}")
    public String shop(@PathVariable String categoryName, Model model) {
        List<Product> products = this.productService.getProductsByCategory(categoryName);

        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);

        return "shop";
    }
}
