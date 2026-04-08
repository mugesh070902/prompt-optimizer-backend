package com.promptoptimizer.dto;

import java.time.LocalDateTime;

public class PromptHistoryDto {

    private Long id;
    private String originalPrompt;
    private String improvedPrompt;
    private String issues;
    private int tokenCountBefore;
    private int tokenCountAfter;
    private int tokenReduction;
    private LocalDateTime createdAt;

    public PromptHistoryDto(Long id, String originalPrompt, String improvedPrompt, String issues,
                            int tokenCountBefore, int tokenCountAfter, int tokenReduction,
                            LocalDateTime createdAt) {
        this.id = id;
        this.originalPrompt = originalPrompt;
        this.improvedPrompt = improvedPrompt;
        this.issues = issues;
        this.tokenCountBefore = tokenCountBefore;
        this.tokenCountAfter = tokenCountAfter;
        this.tokenReduction = tokenReduction;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalPrompt() {
        return originalPrompt;
    }

    public String getImprovedPrompt() {
        return improvedPrompt;
    }

    public String getIssues() {
        return issues;
    }

    public int getTokenCountBefore() {
        return tokenCountBefore;
    }

    public int getTokenCountAfter() {
        return tokenCountAfter;
    }

    public int getTokenReduction() {
        return tokenReduction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
