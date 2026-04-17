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

    // GETTERS & SETTERS
    public Long getId() { return id; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public int getOriginalTokens() { return originalTokens; }
    public void setOriginalTokens(int originalTokens) { this.originalTokens = originalTokens; }

    public int getOptimizedTokens() { return optimizedTokens; }
    public void setOptimizedTokens(int optimizedTokens) { this.optimizedTokens = optimizedTokens; }

    public double getSavedCost() { return savedCost; }
    public void setSavedCost(double savedCost) { this.savedCost = savedCost; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
