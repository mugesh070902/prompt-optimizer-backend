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

        String desc = input.getOrDefault("desc", "");
        String frontend = input.getOrDefault("frontend", "");
        String backend = input.getOrDefault("backend", "");
        String database = input.getOrDefault("database", "");

        String optimizedPrompt = desc + " | " + frontend + " " + backend + " " + database;

        int tokens = TokenUtil.estimateTokens(optimizedPrompt);
        double cost = TokenUtil.estimateCost(tokens);

        String aiResponse = callAI(optimizedPrompt);

        // 🔥 AGENT.MD GENERATION
        String agentMd = """
# AI Agent Instructions

## Objective
""" + desc + """

## Tech Stack
- Frontend: """ + frontend + """
- Backend: """ + backend + """
- Database: """ + database + """

## Steps
1. Understand requirements
2. Design architecture
3. Implement modules
4. Store data in DB

## Output
- Clean code
- Scalable system
""";

        // SAVE TO DB
        PromptHistory history = new PromptHistory();
        history.setPrompt(optimizedPrompt);
        history.setResponse(aiResponse);
        history.setOptimizedTokens(tokens);
        history.setSavedCost(cost);

        repo.save(history);

        Map<String, Object> result = new HashMap<>();
        result.put("improvedPrompt", aiResponse);
        result.put("agentMd", agentMd);
        result.put("savedTokens", tokens);
        result.put("savedCost", cost);

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
                                    Map.of("role", "system", "content", "Provide structured output."),
                                    Map.of("role", "user", "content", prompt)
                            )
                    ))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map choice = (Map) ((List) response.get("choices")).get(0);
            Map message = (Map) choice.get("message");

            return message.get("content").toString();

        } catch (Exception e) {
            return "❌ AI ERROR";
        }
    }
}
