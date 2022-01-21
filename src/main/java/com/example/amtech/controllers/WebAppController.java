package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * Controller managing the classic pages of the application.
 * It provides an endpoint to :
 * - home page
 * - about page
 * - contact page
 * - checkout page
 * - error page
 */
@Controller
public class WebAppController extends SessionController {

    private final ShoppingCartService shoppingCartService;
    private final HttpSession httpSession;

    public WebAppController(CategoryService categoryService, ShoppingCartService shoppingCartService, HttpSession httpSession) {
        super(categoryService);
        this.shoppingCartService = shoppingCartService;
        this.httpSession = httpSession;
    }

    @ModelAttribute(ShoppingCart.ATTR_NAME)
    public ShoppingCart cart(@ModelAttribute ShoppingCart shoppingCart) {
        Object userId = httpSession.getAttribute("user_id");
        if (userId != null) { // True when logged
            ShoppingCart dbCart = shoppingCartService.retrieveUserShoppingCart(String.valueOf(userId));
            shoppingCart.setAll(dbCart);
        }
        return shoppingCartService.checkCartIntegrity(shoppingCart);
    }

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
