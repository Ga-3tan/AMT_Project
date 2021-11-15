package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.models.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class ShoppingCartController extends SessionController {

    @GetMapping("/shopping-cart")
    public String product(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.addToCart(new Product("1", "1.jpg", "prod1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"}));
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "shopping-cart";
    }
}