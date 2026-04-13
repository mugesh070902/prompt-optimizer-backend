package com.promptoptimizer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class OpenAIService {

    private final WebClient webClient;

    // 🔥 Paste your OpenRouter key here
    private final String API_KEY = "sk-prompt-optimizer-backend-REPLACE-WITH-YOUR-NEW-KEY";

    public OpenAIService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + API_KEY)
                .defaultHeader("HTTP-Referer", "http://localhost:3000")
                .defaultHeader("X-Title", "Prompt Optimizer")
                .build();
    }
public String generatePrompt(String input) {
    try {
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are an expert prompt engineer."),
                        Map.of("role", "user", "content", input)
                }
        );

        Map response = webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + API_KEY)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map choice = (Map)((java.util.List)response.get("choices")).get(0);
        Map message = (Map) choice.get("message");

        return message.get("content").toString();

    } catch (Exception e) {
        e.printStackTrace(); // 🔥 IMPORTANT
        return "❌ AI ERROR";
    }
}

    public String generateAgentMd(String input) {
        try {
            Map<String, Object> requestBody = Map.of(
                    "model", "openrouter/auto",
                    "messages", new Object[]{
                            Map.of("role", "system", "content", "Generate a clean agent.md with Role, Task, Requirements, Constraints, Output."),
                            Map.of("role", "user", "content", input)
                    }
            );

            Map response = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map choice = (Map)((java.util.List)response.get("choices")).get(0);
            Map message = (Map) choice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ OpenRouter error";
        }
    }
}
