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

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@Controller
public class ShoppingCartController extends SessionController {

    private ProductService productService;

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.addToCart(productService.getAllProducts().get(0));
        shoppingCart.addToCart(productService.getAllProducts().get(1));
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "shopping-cart";
    }

    @RequestMapping(value="/shopping-cart", method=RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postShoppingCart(Model model, @RequestBody MultiValueMap<String, String> formData, @ModelAttribute ShoppingCart shoppingCart) {

        // Updates all products values
        Iterator<String> prodIds = formData.get("form_product_id").iterator();
        Iterator<String> prodQty = formData.get("form_product_quantity").iterator();

        while (prodIds.hasNext()) {
            Product p = productService.getById(prodIds.next());
            if (p != null) {
                int newQty = Integer.parseInt(prodQty.next());

                // Removes product if qty = 0
                if (newQty <= 0) shoppingCart.remove(p);
                else shoppingCart.getProducts().put(p, newQty);
            }
        }

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "shopping-cart";
    }

    @RequestMapping(value="/shopping-cart-remove", method=RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postreRemoveProductShoppingCart(Model model, @RequestBody MultiValueMap<String, String> formData, @ModelAttribute ShoppingCart shoppingCart) {


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