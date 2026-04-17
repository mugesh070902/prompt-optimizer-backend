package com.promptoptimizer.service;

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

    // SIMPLE CACHE
    private final Map<String, Map<String, Object>> cache = new HashMap<>();

    public Map<String, Object> process(Map<String, String> input) {

        String originalPrompt = buildOriginalPrompt(input);
        String optimizedPrompt = buildOptimizedPrompt(input);

        int originalTokens = TokenUtil.estimateTokens(originalPrompt);
        int optimizedTokens = TokenUtil.estimateTokens(optimizedPrompt);

        double originalCost = TokenUtil.estimateCost(originalTokens);
        double optimizedCost = TokenUtil.estimateCost(optimizedTokens);

        // CACHE CHECK
        if (cache.containsKey(optimizedPrompt)) {
            Map<String, Object> cached = cache.get(optimizedPrompt);
            cached.put("cached", true);
            return cached;
        }

        String aiResponse = callAI(optimizedPrompt);

        Map<String, Object> result = new HashMap<>();
        result.put("originalTokens", originalTokens);
        result.put("optimizedTokens", optimizedTokens);
        result.put("originalCost", originalCost);
        result.put("optimizedCost", optimizedCost);
        result.put("savedTokens", originalTokens - optimizedTokens);
        result.put("savedCost", originalCost - optimizedCost);
        result.put("improvedPrompt", aiResponse);

        cache.put(optimizedPrompt, result);

        return result;
    }

    // ❌ UNOPTIMIZED
    private String buildOriginalPrompt(Map<String, String> in) {
        return "Project Description: " + in.get("desc") +
                "\nFrontend: " + in.get("frontend") +
                "\nBackend: " + in.get("backend") +
                "\nDatabase: " + in.get("database") +
                "\nProvide full explanation, steps, best practices.";
    }

    // ✅ OPTIMIZED
    private String buildOptimizedPrompt(Map<String, String> in) {

        StringBuilder sb = new StringBuilder();

        if (in.get("desc") != null)
            sb.append(in.get("desc")).append(" | ");

        if (in.get("frontend") != null)
            sb.append(in.get("frontend")).append(" ");

        if (in.get("backend") != null)
            sb.append(in.get("backend")).append(" ");

        if (in.get("database") != null)
            sb.append(in.get("database"));

        return sb.toString().trim();
    }

    private String callAI(String prompt) {

        Map response = client.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + API_KEY)
                .bodyValue(Map.of(
                        "model", "openai/gpt-3.5-turbo",
                        "max_tokens", 300,
                        "messages", List.of(
                                Map.of("role", "system", "content",
                                        "You are an expert. Always provide architecture, steps, best practices."),
                                Map.of("role", "user", "content", prompt)
                        )
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map choice = (Map) ((List) response.get("choices")).get(0);
        Map message = (Map) choice.get("message");

        return message.get("content").toString();
    }
}
