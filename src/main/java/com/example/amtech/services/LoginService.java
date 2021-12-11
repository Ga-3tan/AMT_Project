package com.example.amtech.services;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class LoginService {

    private final String loginServiceIP = "http://localhost:8083"; //TODO change for deploy
    public static final String CONFLICT = "The username already exist";
    public static final String INVALID = "The data you entered are not valid";
    public static final String FORBIDDEN = "The credentials are incorrect";
    private final WebClient webClient = WebClient.create(loginServiceIP);

    public ResponseEntity<String> registerUser(JSONObject data) {
        return webClient.post()
                .uri("/accounts/register")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(data.toString()))
//                .accept(MediaType.APPLICATION_JSON)
//                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        res -> Mono.empty()
//                        res -> Mono.error(new WebClientResponseException(res.statusCode().value(), res.statusCode().getReasonPhrase(), res.bodyToMono(String.class).toString().getBytes(StandardCharsets.UTF_8)))
//                        res -> res.bodyToMono(String.class).map(BadRequestException::new)
                )
                .toEntity(String.class)
                .block();
    }

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