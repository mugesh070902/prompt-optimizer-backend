package com.promptoptimizer.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for prompt analysis responses.
 * Returns analysis score, identified issues, and improved prompt.
 */
public class PromptResponse {

    private Long id;
    private String promptText;
    private Double analysisScore;
    private List<String> issues;
    private String improvedPrompt;
    private String agentMd;
    private LocalDateTime createdAt;

    // Constructors
    public PromptResponse() {}

    public PromptResponse(Long id, String promptText, Double analysisScore, List<String> issues, String improvedPrompt, String agentMd, LocalDateTime createdAt) {
        this.id = id;
        this.promptText = promptText;
        this.analysisScore = analysisScore;
        this.issues = issues;
        this.improvedPrompt = improvedPrompt;
        this.agentMd = agentMd;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public Double getAnalysisScore() {
        return analysisScore;
    }

    public void setAnalysisScore(Double analysisScore) {
        this.analysisScore = analysisScore;
    }

    public List<String> getIssues() {
        return issues;
    }

    public void setIssues(List<String> issues) {
        this.issues = issues;
    }

    public String getImprovedPrompt() {
        return improvedPrompt;
    }

    public void setImprovedPrompt(String improvedPrompt) {
        this.improvedPrompt = improvedPrompt;
    }

    public String getAgentMd() {
        return agentMd;
    }

    public void setAgentMd(String agentMd) {
        this.agentMd = agentMd;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PromptResponse{" +
                "id=" + id +
                ", analysisScore=" + analysisScore +
                ", issuesCount=" + (issues != null ? issues.size() : 0) +
                '}';


    }

}
