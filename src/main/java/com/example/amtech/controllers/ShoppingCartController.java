package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Controller
public class ShoppingCartController extends SessionController {

    private ProductService productService;
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);

        return "shopping-cart";
    }

    @RequestMapping(value="/shopping-cart", method=RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postShoppingCart(Model model, @RequestBody MultiValueMap<String, String> formData, @ModelAttribute ShoppingCart shoppingCart) {
        if (formData.get("update_cart") != null) {
            updateShoppingCart(shoppingCart, formData);
        } else if (formData.get("empty_cart") != null) {
            shoppingCart.emptyCart();
        }

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "shopping-cart";
    }

    private void updateShoppingCart(ShoppingCart shoppingCart, MultiValueMap<String, String> formData) {
        // Updates all products values
        try {
            Iterator<String> prodIds = formData.get("form_product_id").iterator();
            Iterator<String> prodQty = formData.get("form_product_quantity").iterator();

            while (prodIds.hasNext()) {
                Product p = productService.getById(prodIds.next());
                if (p != null) {
                    int newQty = Integer.parseInt(prodQty.next());
                    shoppingCart.setProduct(p, newQty);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}