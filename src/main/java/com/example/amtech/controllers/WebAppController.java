package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@AllArgsConstructor
@Controller
public class WebAppController extends SessionController {

    CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/")
    public String homepage(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "contact";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "checkout";
    }

    @GetMapping("/error")
    public String error(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute("cart", shoppingCart);
        return "error";
    }
}
