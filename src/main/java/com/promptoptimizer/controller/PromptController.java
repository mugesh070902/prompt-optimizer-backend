package com.promptoptimizer.controller;

import com.promptoptimizer.service.OptimizedAIService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PromptController {

    private final OptimizedAIService service;

    public PromptController(OptimizedAIService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> req) {
        return service.process(req);
    }
}
