package com.promptoptimizer.controller;
import com.promptoptimizer.service.OpenAIService;

import com.promptoptimizer.service.PromptAnalysisService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PromptController {

    private final PromptAnalysisService service;
    private final OpenAIService aiService;

    public PromptController(PromptAnalysisService service, OpenAIService aiService) {
        this.service = service;
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> request) {

        System.out.println("🚀 NEW CONTROLLER RUNNING");

        String prompt = request.get("prompt");

        List<String> issues = service.analyzePrompt(prompt);
        double score = service.calculateScore(prompt, issues);

        // 🔥 AI CALL
        String improved = aiService.generatePrompt(prompt);

        String agentMd = aiService.generateAgentMd(prompt);
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
    @GetMapping("/download")
public ResponseEntity<byte[]> downloadAgentMd(@RequestParam String content) {

    byte[] fileContent = content.getBytes();

    return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=agent.md")
            .header("Content-Type", "text/markdown")
            .body(fileContent);
}
}