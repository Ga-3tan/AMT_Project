package com.example.amtech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {

    @GetMapping("/")
    public String homepage(Model model) {
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        return "checkout";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/shopping-cart")
    public String cart(Model model) {
        return "shopping-cart";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }
}
