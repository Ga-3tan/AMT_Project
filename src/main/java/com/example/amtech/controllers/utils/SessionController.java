package com.example.amtech.controllers.utils;

import com.example.amtech.models.Category;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
@SessionAttributes("shoppingCart")
public class SessionController {

    protected CategoryService categoryService;

    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }
}
