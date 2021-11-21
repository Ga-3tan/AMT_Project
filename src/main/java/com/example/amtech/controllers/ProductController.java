package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import com.example.amtech.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class ProductController extends SessionController {

    private ProductService productService;

    @GetMapping("/product/{id}")
    public String product(@PathVariable String id, Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("products", productService.getAllProducts());
        return "product";
    }

    @RequestMapping(value="/product/{id}", method=RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postShoppingCart(@PathVariable String id, Model model, @RequestBody MultiValueMap<String, String> formData, @ModelAttribute ShoppingCart shoppingCart) {

        Product concernedProduct = productService.getById(id);

        if (formData.get("add_product") != null) {
            int newQty = Integer.parseInt(formData.get("product_quantity").get(0));
            shoppingCart.setProduct(concernedProduct, newQty);
        }

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        model.addAttribute("product", concernedProduct);
        return "product";
    }
}
