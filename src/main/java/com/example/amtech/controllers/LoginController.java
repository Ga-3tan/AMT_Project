package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.LoginService;
import com.example.amtech.services.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller managing the login/sign up pages of the application.
 * It provides a /signup endpoint for registration
 *
 * (Attention: the login is handled via AuthenticationProvider)
 */
@Slf4j
@Controller
public class LoginController extends SessionController {

    private final LoginService loginService;
    private final ShoppingCartService shoppingCartService;

    public LoginController(CategoryService categoryService, LoginService loginService, ShoppingCartService shoppingCartService) {
        super(categoryService);
        this.loginService = loginService;
        this.shoppingCartService = shoppingCartService;
    }

    @ModelAttribute(ShoppingCart.ATTR_NAME)
    public ShoppingCart cart(@ModelAttribute ShoppingCart shoppingCart) {
        return shoppingCartService.checkCartIntegrity(shoppingCart);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {

        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

        ResponseEntity<String> response = loginService.registerUser(body);
        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            log.info("USER correctly registered");
            return "redirect:/login";
        } else if (response.getStatusCode().equals(HttpStatus.CONFLICT)) {
            JSONObject res = new JSONObject(response.getBody());
            model.addAttribute("alreadyExist", res.get("error").toString());
        } else { // INVALID
            JSONObject res = new JSONObject(response.getBody());
            JSONArray err = res.getJSONArray("errors");
            String msg = ((JSONObject) err.get(0)).get("message").toString();
            model.addAttribute("error", msg);
        }
        return "signup";
    }
}
