package com.promptoptimizer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class TokenService {

    private final WebClient client = WebClient.create("http://localhost:5001");

    public int getRealTokens(String text) {

        Map response = client.post()
                .uri("/tokens")
                .bodyValue(Map.of("text", text))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return (int) response.get("tokens");
    }
}
