package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@AllArgsConstructor
@Controller
public class WebAppController extends SessionController {

    CategoryService categoryService;
    private ShoppingCartService shoppingCartService;
    private HttpSession httpSession;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
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
