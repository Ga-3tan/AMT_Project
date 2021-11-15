package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import com.example.amtech.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Controller
public class CategoryController extends SessionController {

    private ProductService productService;

    @GetMapping("/category")
    public String shop(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        List<Product> products = this.productService.getAllProducts();

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("products", products);
        model.addAttribute("categoryName", "All categories");

        return "shop";
    }

    @GetMapping("/category/{categoryName}")
    public String shop(@PathVariable String categoryName, Model model, @ModelAttribute ShoppingCart shoppingCart) {
        List<Product> products = this.productService.getProductsByCategory(categoryName);

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);

        return "shop";
    }
}
