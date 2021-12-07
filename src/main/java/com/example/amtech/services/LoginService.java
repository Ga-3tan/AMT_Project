package com.example.amtech.services;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class LoginService {

    private final String loginServiceIP = "http://localhost:8083"; //TODO change for deploy
    public static final String CONFLICT = "The username already exist";
    public static final String INVALID = "The data you entered are not valid";
    public static final String FORBIDDEN = "The credentials are incorrect";
    private final WebClient webClient = WebClient.create(loginServiceIP);
/*
    public String registerUser(JSONObject data) {
        return webClient.post()
                .uri("/accounts/register")
                .bodyValue(data)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .exchangeToMono(res -> {
                    if (res.statusCode().equals(HttpStatus.CREATED)) {
                        return res.bodyToMono(String.class);
                    } else if (res.statusCode().equals(HttpStatus.CONFLICT)) {
                        return Mono.just(CONFLICT);
                    } else {
                        return Mono.just(INVALID);
                    }
                })
                .block();
    }

    public JSONObject signIn(JSONObject body) {
        ResponseEntity<String> response = webClient
                .post()
                .uri("/auth/login")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(body.toString()))
                .retrieve()
                .onStatus(
                        status -> status.value() == 403,
                        clientResponse -> Mono.empty()
                )
                .toEntity(String.class)
                .block();

        return new JSONObject(response.getBody());
    }*/


    public JSONObject postRequest(String uri, JSONObject body) {
        ResponseEntity<String> response = webClient
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(body.toString()))
                .retrieve()
                .onStatus(
                        status -> status.value() == 403,
                        clientResponse -> Mono.empty()
                )
                .toEntity(String.class)
                .block();

        return new JSONObject(response.getBody());
    }

}