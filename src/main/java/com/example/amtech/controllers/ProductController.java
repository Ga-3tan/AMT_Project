package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.ProductService;
import com.example.amtech.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@AllArgsConstructor
@Controller
public class ProductController extends SessionController {

    private ProductService productService;

    @GetMapping("/product/{id}")
    public String product(@PathVariable String id, Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("product",productService.getById(id));
        return "product";
    }
}
