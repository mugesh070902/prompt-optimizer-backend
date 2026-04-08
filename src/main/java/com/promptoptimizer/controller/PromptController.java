package com.promptoptimizer.controller;

import com.promptoptimizer.service.PromptAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PromptController {

    private final PromptAnalysisService service;

    public PromptController(PromptAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> request) {

        String prompt = request.get("prompt");

        List<String> issues = service.analyzePrompt(prompt);
        double score = service.calculateScore(prompt, issues);
        String improved = service.generateImprovedPrompt(prompt, issues);
        String agentMd = service.generateAgentMd(prompt, issues);

        return Map.of(
                "promptText", prompt,
                "analysisScore", score,
                "issues", issues,
                "improvedPrompt", improved,
                "agentMd", agentMd
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}