package com.promptoptimizer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "prompt_history")
public class PromptHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_prompt", columnDefinition = "TEXT")
    private String originalPrompt;

    @Column(name = "improved_prompt", columnDefinition = "TEXT")
    private String improvedPrompt;

    @Column(name = "agent_md", columnDefinition = "TEXT")
    private String agentMd;

    @Column(name = "issues", columnDefinition = "TEXT")
    private String issues;

    @Column(name = "score_clarity")
    private Integer scoreClarity;

    @Column(name = "score_efficiency")
    private Integer scoreEfficiency;

    @Column(name = "score_structure")
    private Integer scoreStructure;

    @Column(name = "token_count_before")
    private Integer tokenCountBefore;

    @Column(name = "token_count_after")
    private Integer tokenCountAfter;

    @Column(name = "token_reduction")
    private Integer tokenReduction;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalPrompt() {
        return originalPrompt;
    }

    public void setOriginalPrompt(String originalPrompt) {
        this.originalPrompt = originalPrompt;
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

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public Integer getScoreClarity() {
        return scoreClarity;
    }

    public void setScoreClarity(Integer scoreClarity) {
        this.scoreClarity = scoreClarity;
    }

    public Integer getScoreEfficiency() {
        return scoreEfficiency;
    }

    public void setScoreEfficiency(Integer scoreEfficiency) {
        this.scoreEfficiency = scoreEfficiency;
    }

    public Integer getScoreStructure() {
        return scoreStructure;
    }

    public void setScoreStructure(Integer scoreStructure) {
        this.scoreStructure = scoreStructure;
    }

    public Integer getTokenCountBefore() {
        return tokenCountBefore;
    }

    public void setTokenCountBefore(Integer tokenCountBefore) {
        this.tokenCountBefore = tokenCountBefore;
    }

    public Integer getTokenCountAfter() {
        return tokenCountAfter;
    }

    public void setTokenCountAfter(Integer tokenCountAfter) {
        this.tokenCountAfter = tokenCountAfter;
    }

    public Integer getTokenReduction() {
        return tokenReduction;
    }

    public void setTokenReduction(Integer tokenReduction) {
        this.tokenReduction = tokenReduction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
