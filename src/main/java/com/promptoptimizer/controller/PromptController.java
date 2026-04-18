package com.promptoptimizer.controller;

import com.promptoptimizer.service.OptimizedAIService;
import com.promptoptimizer.repository.PromptHistoryRepository;
import com.promptoptimizer.model.PromptHistory;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PromptController {

    private final OptimizedAIService service;
    private final PromptHistoryRepository repo;

    public PromptController(OptimizedAIService service, PromptHistoryRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping("/")
    public String home() {
        return "API Working ✅";
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> req) {
        return service.process(req);
    }

    @GetMapping("/history")
    public List<PromptHistory> getHistory() {
        return repo.findAll();
    }
}
