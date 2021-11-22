package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class WebAppController extends SessionController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String homepage(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "contact";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "checkout";
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "login";
    }

    @GetMapping("/error")
    public String error(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "error";
    }
}
