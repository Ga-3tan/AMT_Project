package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import com.example.amtech.models.ShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class ShoppingCartController extends SessionController {

    private ProductService productService;

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.addToCart(productService.getAllProducts().get(0));
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "shopping-cart";
    }

    @RequestMapping(value="/shopping-cart", method=RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postShoppingCart(Model model, @RequestBody MultiValueMap<String, String> formData, @ModelAttribute ShoppingCart shoppingCart) {

        int quantity = Integer.parseInt(formData.get("form_product_quantity").get(0));
        Product p = productService.getAllProducts().get(0);
        shoppingCart.getProducts().put(p, quantity);
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "shopping-cart";
    }

    private void updateShoppingCart() {

    }

    private void emptyShoppingCart() {

    }

    private void sfefeShoppingCart() {

    }
}