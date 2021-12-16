package com.example.amtech.services;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
public class LoginService {

    private final String loginServiceIP = "http://localhost:8083"; //TODO change for deploy
    private final WebClient webClient = WebClient.create(loginServiceIP);

    public ResponseEntity<String> registerUser(JSONObject data) {
        return webClient.post()
                .uri("/accounts/register")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(data.toString()))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        res -> Mono.empty()
                )
                .toEntity(String.class)
                .block();
    }

    // DPE - Pour la fonction d'en dessus vous ne passez pas l'URI. Choisissez une manière. Je vote pour ne pas passer l'URI.
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