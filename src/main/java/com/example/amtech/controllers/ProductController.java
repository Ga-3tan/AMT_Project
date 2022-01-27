package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ProductService;
import com.example.amtech.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller managing the application page showing product details.
 * It provides an endpoint to get a product details and an endpoint
 * to add the product to the shopping cart.
 */
@Controller
public class ProductController extends SessionController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ProductController(CategoryService categoryService, ProductService productService, ShoppingCartService shoppingCartService) {
        super(categoryService);
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/product/{productId}")
    public String product(@PathVariable String productId, Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCartService.checkCartIntegrity(shoppingCart));
        model.addAttribute("product", productService.getById(productId));
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("products_size", products.size());
        return "product";
    }

    @PostMapping(value="/product/{productId}")
    public String postShoppingCart(@PathVariable String productId,
                                   Model model,
                                   @RequestBody MultiValueMap<String, String> formData,
                                   @ModelAttribute ShoppingCart shoppingCart) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("products_size" ,products.size());


        Product concernedProduct = productService.getById(productId);

        if (formData.get("add_product") != null) {
            int qty = shoppingCart.getProductQuantity(concernedProduct);
            int newQty = Integer.parseInt(formData.get("product_quantity").get(0));
            shoppingCart.setProduct(concernedProduct, qty + newQty);
        }

        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCartService.checkCartIntegrity(shoppingCart));
        model.addAttribute("product", concernedProduct);
        return "product";
    }
}
