package com.example.amtech.models;

import com.example.amtech.repository.CustomUserRepository;
import com.example.amtech.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepo;

    private CustomUserRepository customUserRepo;

    public JSONObject registerUser(String data, String loginService) {
        WebClient client = WebClient.create(loginService);
        ResponseEntity<String> response = client.post()
                .uri("/accounts/register")
                .bodyValue(data)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                // traiter les 2 erreurs ensembles ?
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.empty())
                .toEntity(String.class)
                .block();

        return new JSONObject(response.getBody());
    }

    // CRUD operations

    // CREATE
    public String createUser(String id, String username, String role) {
        User user1 = new User(id, username, role);
        System.out.println(user1);
        User user = userRepo.save(user1);
        return user.getId();
    }

    // READ
    public User getUserById(String id) {
        return userRepo.findUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    public ShoppingCart getUserShoppingCart(String id) {
        return customUserRepo.getShoppingCartFromUserById(id);
    }

    // UPDATE

    // DELETE

}
