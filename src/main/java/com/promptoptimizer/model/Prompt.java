package com.promptoptimizer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Entity class representing a prompt in the database.
 * Stores original prompt, analysis score, identified issues, and improved version.
 */
@Entity
@Table(name = "prompts", indexes = {
    @Index(name = "idx_createdAt", columnList = "createdAt"),
    @Index(name = "idx_score", columnList = "analysisScore")
})
public class Prompt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT", nullable = false, unique = true)
    @NotBlank(message = "Prompt text cannot be blank")
    private String promptText;

    @Column(nullable = false)
    private Double analysisScore;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String issues;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "Improved prompt cannot be blank")
    private String improvedPrompt;

    @Lob
    @Column(nullable = false)
    private String agentMd;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public Prompt() {}

    public Prompt(String promptText, Double analysisScore, String issues, String improvedPrompt, String agentMd) {
        this.promptText = promptText;
        this.analysisScore = analysisScore;
        this.issues = issues;
        this.improvedPrompt = improvedPrompt;
        this.agentMd = agentMd;
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

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Prompt{" +
                "id=" + id +
                ", promptText='" + promptText + '\'' +
                ", analysisScore=" + analysisScore +
                ", createdAt=" + createdAt +
                '}';
    }
}
