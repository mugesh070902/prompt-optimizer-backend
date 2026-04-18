package com.promptoptimizer.controller;

import com.promptoptimizer.service.OptimizedAIService;
import com.promptoptimizer.repository.PromptHistoryRepository;
import com.promptoptimizer.model.PromptHistory;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PromptController {

    private final OptimizedAIService service;
    private final PromptHistoryRepository repo;

    public PromptController(OptimizedAIService service, PromptHistoryRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> req) {
        return service.process(req);
    }

    // 🔥 IMPORTANT ENDPOINT
    @GetMapping("/history")
    public List<PromptHistory> getHistory() {
        return repo.findAll();
    }
}
