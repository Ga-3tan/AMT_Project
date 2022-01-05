package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.services.CategoryService;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.services.LoginService;
import com.example.amtech.services.ShoppingCartService;
import com.example.amtech.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@AllArgsConstructor
@Controller
public class LoginController extends SessionController {

    private CategoryService categoryService;
    private LoginService loginService;
    private HttpServletResponse response;
    private JwtUtil jwtUtil;

    private ShoppingCartService shoppingCartService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
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
                               Model model)
    {
        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

        ResponseEntity<String> response = loginService.registerUser(body);
        if (response.getStatusCode().equals(HttpStatus.CREATED)) {
            System.out.println("USER correctly registered");
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
