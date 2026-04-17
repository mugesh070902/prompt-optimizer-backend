package com.promptoptimizer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PromptHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prompt;

    @Column(length = 5000)
    private String response;

    private int originalTokens;
    private int optimizedTokens;

    private double savedCost;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
    public void setPrompt(String prompt) { this.prompt = prompt; }
    public void setResponse(String response) { this.response = response; }
    public void setOriginalTokens(int originalTokens) { this.originalTokens = originalTokens; }
    public void setOptimizedTokens(int optimizedTokens) { this.optimizedTokens = optimizedTokens; }
    public void setSavedCost(double savedCost) { this.savedCost = savedCost; }
}
