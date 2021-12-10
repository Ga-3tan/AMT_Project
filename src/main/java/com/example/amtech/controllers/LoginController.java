package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.models.UserService;
import com.example.amtech.services.LoginService;
import com.example.amtech.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@AllArgsConstructor
@Controller
public class LoginController extends SessionController {

    CategoryService categoryService;
    UserService userService;
    LoginService loginService;
    HttpServletResponse response;
    JwtUtil jwtUtil;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "signup";
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

        String response = loginService.postRequest("/accounts/register", body).toString();
        if (response.equals(LoginService.CONFLICT)) {
            System.out.println("Enter to: " + LoginService.CONFLICT);
            // TODO rester sur la page et afficher message
            /* Response body
            {
              "error": "The username already exists"
            }*/
        } else if (response.equals(LoginService.INVALID)) {
            System.out.println("Enter to: " + LoginService.INVALID);
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
            System.out.println("USER correctly registered");
            //TODO popUp/modal/alert user correctly registered
        }
        return "redirect:/";
    }
}
