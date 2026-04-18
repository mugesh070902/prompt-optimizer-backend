package com.promptoptimizer.service;

import com.promptoptimizer.repository.PromptHistoryRepository;
import com.promptoptimizer.model.PromptHistory;
import com.promptoptimizer.util.TokenUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class OptimizedAIService {

    @Value("${openai.api.key}")
    private String API_KEY;

    private final WebClient client = WebClient.create("https://openrouter.ai/api/v1");

    private final PromptHistoryRepository repo;

    public OptimizedAIService(PromptHistoryRepository repo) {
        this.repo = repo;
    }

public Map<String, Object> process(Map<String, String> input) {

    Map<String, Object> result = new HashMap<>();

    try {

        String desc = input.getOrDefault("desc", "No description");
        String frontend = input.getOrDefault("frontend", "Not selected");
        String backend = input.getOrDefault("backend", "Not selected");
        String database = input.getOrDefault("database", "Not selected");

        String optimizedPrompt = desc + " | " + frontend + " " + backend + " " + database;

        int tokens = TokenUtil.estimateTokens(optimizedPrompt);
        double cost = TokenUtil.estimateCost(tokens);

        // 🔥 SAFE AI CALL
        String aiResponse = callAI(optimizedPrompt);

        if (aiResponse == null || aiResponse.isEmpty()) {
            aiResponse = "Generated prompt: " + optimizedPrompt;
        }

        // 🔥 AGENT MD ALWAYS GENERATED
        String agentMd = """
# AI Agent Instructions

## Objective
""" + desc + """

## Tech Stack
- Frontend: """ + frontend + """
- Backend: """ + backend + """
- Database: """ + database + """

## Steps
1. Understand requirement
2. Design architecture
3. Implement modules
4. Store data

## Output
- Clean code
- Scalable system
""";

        // 🔥 SAFE DB SAVE
        try {
            PromptHistory history = new PromptHistory();
            history.setPrompt(optimizedPrompt);
            history.setResponse(aiResponse);
            history.setOptimizedTokens(tokens);
            history.setSavedCost(cost);
            repo.save(history);
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        result.put("improvedPrompt", aiResponse);
        result.put("agentMd", agentMd);
        result.put("savedTokens", tokens);
        result.put("savedCost", cost);

    } catch (Exception e) {

        result.put("improvedPrompt", "❌ Backend Error: " + e.getMessage());
        result.put("agentMd", "Error generating agent.md");
        result.put("savedTokens", 0);
        result.put("savedCost", 0);
    }

    return result;
}
private String callAI(String prompt) {
    try {

        Map response = client.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + API_KEY)
                .bodyValue(Map.of(
                        "model", "openai/gpt-3.5-turbo",
                        "max_tokens", 300,
                        "messages", List.of(
                                Map.of("role", "system", "content", "Give structured output"),
                                Map.of("role", "user", "content", prompt)
                        )
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null) return null;

        Map choice = (Map) ((List) response.get("choices")).get(0);
        Map message = (Map) choice.get("message");

        return message.get("content").toString();

    } catch (Exception e) {
        System.out.println("AI ERROR: " + e.getMessage());
        return null; // 🔥 IMPORTANT (no crash)
    }
}
}
