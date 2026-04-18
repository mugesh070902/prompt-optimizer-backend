package com.promptoptimizer.model;

import jakarta.persistence.*;

@Entity
public class PromptHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prompt;

    @Column(length = 5000)
    private String response;

    private int optimizedTokens;
    private double savedCost;

    // GETTERS
    public String getPrompt() { return prompt; }
    public int getOptimizedTokens() { return optimizedTokens; }

    // SETTERS
    public void setPrompt(String prompt) { this.prompt = prompt; }
    public void setResponse(String response) { this.response = response; }
    public void setOptimizedTokens(int optimizedTokens) { this.optimizedTokens = optimizedTokens; }
    public void setSavedCost(double savedCost) { this.savedCost = savedCost; }
}
