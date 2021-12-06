package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.models.UserService;
import com.example.amtech.services.LoginService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController extends SessionController {

    CategoryService categoryService;
    UserService userService;
    LoginService loginService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping()
    public String login(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "login";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @ModelAttribute ShoppingCart shoppingCart,
                               Model model) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);

        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

//        ResponseEntity<String> response = loginService.registerUser(body);
        String response = loginService.registerUser(body);
        if (response.equals(LoginService.CONFLICT)) {
            // TODO rester sur la page et afficher message
            /* Response body
            {
              "error": "The username already exists"
            }*/
        } else if (response.equals(LoginService.INVALID)) {
            // TODO rester sur la page et afficher message
            /*
            {
              "errors": [
                {
                  "property": "string",
                  "message": "string"
                }
              ]
            }*/
        } else {
            JSONObject user = new JSONObject(response);
            userService.createUser(user.getString("id"),
                    user.getString("username"),
                    user.getString("role"));
        }
        // TODO créer cookie

        return "redirect:/home";
    }

    @PostMapping("/signin")
    public String logInUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @ModelAttribute ShoppingCart shoppingCart,
                            Model model) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

        String response = loginService.signIn(body);
        if (response.equals(LoginService.FORBIDDEN)) {
            // TODO rester sur la page et afficher message
        } else {
            JSONObject user = new JSONObject(response);
            // TODO
        }

        return "redirect:/home";
    }
}
