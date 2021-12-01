package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class WebAppController extends SessionController {
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    // DPE - La comme ça je vois de la duplication, est-ce qu'il y aurait un moyen de mieux faire ?
    // Sinon je suis preneur de l'explication de pourquoi pas (En commentaire)
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

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "login";
    }

    @GetMapping("/error")
    public String error(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "error";
    }
}
