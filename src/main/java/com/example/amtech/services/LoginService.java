package com.example.amtech.services;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
public class LoginService {

    private final String loginServiceIP = "localhost:8083";
    public static final String CONFLICT = "The username already exist";
    public static final String INVALID = "The data you entered are not valid";
    public static final String FORBIDDEN = "The credentials are incorrect";

//    public ResponseEntity<String> registerUser(JSONObject data) {
//        WebClient client = WebClient.create(loginServiceIP);
//        return client.post()
//                .uri("/accounts/register")
//                .bodyValue(data)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON)
//                .acceptCharset(StandardCharsets.UTF_8)
//                .retrieve()
//                // traiter les 2 erreurs ensembles ?
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.empty())
//                .toEntity(String.class)
//                .block();
//    }

    public String registerUser(JSONObject data) {
        WebClient client = WebClient.create(loginServiceIP);
        return client.post()
                .uri("/accounts/register")
                .bodyValue(data)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .exchangeToMono(res -> {
                    if (res.statusCode().equals(HttpStatus.CREATED)) {
                        return res.bodyToMono(String.class);
                    } else if (res.statusCode().equals(HttpStatus.CONFLICT)){
                        return Mono.just(CONFLICT);
                    } else {
                        return Mono.just(INVALID);
                    }
                })
                .block();
    }

    public String signIn(JSONObject data) {
        WebClient client = WebClient.create(loginServiceIP);
        return client.post()
                .uri("/accounts/register")
                .bodyValue(data)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .exchangeToMono(res -> {
                    if (res.statusCode().equals(HttpStatus.OK)) {
                        return res.bodyToMono(String.class);
                    } else {
                        return Mono.just(FORBIDDEN);
                    }
                })
                .block();
    }

}
