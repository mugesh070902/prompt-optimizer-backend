package com.promptoptimizer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class OpenAIService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String API_KEY;

    public OpenAIService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String generatePrompt(String input) {
        try {

            Map<String, Object> requestBody = Map.of(
                    "model", "openai/gpt-3.5-turbo",
                    "messages", new Object[]{
                            Map.of("role", "system", "content", "You are an expert prompt engineer."),
                            Map.of("role", "user", "content", input)
                    }
            );

            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("HTTP-Referer", "https://prompt-optimizer.netlify.app")
                    .header("X-Title", "Prompt Optimizer")
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

    public String generateAgentMd(String input) {
        return generatePrompt("Generate agent.md for: " + input);
    }
}
