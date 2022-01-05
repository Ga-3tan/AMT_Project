package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ShoppingCartController extends SessionController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(CategoryService categoryService, ShoppingCartService shoppingCartService) {
        super(categoryService);
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCartService.checkCartIntegrity(shoppingCart));

        return "shopping-cart";
    }

    @PostMapping("/shopping-cart")
    public String postShoppingCart(Model model, @RequestBody MultiValueMap<String, String> formData, @ModelAttribute ShoppingCart shoppingCart) {
        if (formData.get("update_cart") != null) {
            shoppingCartService.updateSessionShoppingCartFromForm(shoppingCart, formData);
        } else if (formData.get("empty_cart") != null) {
            shoppingCart.emptyCart();
        }

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCartService.checkCartIntegrity(shoppingCart));
        return "shopping-cart";
    }
}