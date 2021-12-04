package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.models.User;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController extends SessionController {

    private final String loginService = "localhost:8083";
    CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping()
    public String login(Model model, @ModelAttribute ShoppingCart shoppingCart) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @ModelAttribute ShoppingCart shoppingCart,
                               Model model) {
        model.addAttribute(ShoppingCart.ATTR_NAME, shoppingCart);

//        String body = "{\"username\": " + username + ", \"password\": " + password + "}";
        JSONObject body = new JSONObject()
                .put("username", username)
                .put("password", password);

        // TODO mettre méthode dans USerService
        WebClient client = WebClient.create(loginService);
        ResponseEntity<String> response = client.post()
                .uri("/accounts/register")
                .bodyValue(body)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                // traiter les 2 erreurs ensembles ?
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.empty())
                .toEntity(String.class)
                .block();

        JSONObject user = new JSONObject(response.getBody());
        User newUser = new User(user.getString("id"),
                user.getString("username"),
                user.getString("role"));

        return "redirect:/home";
    }

    @PostMapping("/auth")
    public String logInUser(@RequestBody String request, Model model) {


        return "login";
    }
}
