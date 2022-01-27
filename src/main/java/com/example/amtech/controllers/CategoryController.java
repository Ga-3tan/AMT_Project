package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ProductService;
import com.example.amtech.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller managing the application pages showing products according to category.
 * It provides an endpoint to get all products and an endpoint to get products
 * from a specific category.
 */
@Controller
public class CategoryController extends SessionController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public CategoryController(CategoryService categoryService, ProductService productService, ShoppingCartService shoppingCartService) {
        super(categoryService);
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/categories")
    public String shop(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCartService.checkCartIntegrity(shoppingCart));
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categoryName", "All categories");
        return "shop";
    }

    @GetMapping("/categories/{categoryName}")
    public String shop(@PathVariable String categoryName, Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCartService.checkCartIntegrity(shoppingCart));
        model.addAttribute("products", productService.getProductsByCategory(categoryName));
        model.addAttribute("categoryName", categoryName);
        return "shop";
    }
}
