package com.promptoptimizer.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class PromptAnalysisService {

    // ==============================
    // ENUMS AND CONSTANTS
    // ==============================
    enum PromptCategory {
        GAME, WEBSITE, API, CHATBOT, DATA, UNKNOWN
    }

    private static final Map<PromptCategory, String> CATEGORY_ROLES = Map.of(
            PromptCategory.GAME, "Game Developer",
            PromptCategory.WEBSITE, "Frontend Developer",
            PromptCategory.API, "Backend Developer",
            PromptCategory.CHATBOT, "AI Engineer",
            PromptCategory.DATA, "Data Scientist",
            PromptCategory.UNKNOWN, "AI Assistant"
    );

    private static final Pattern ACTION_PATTERN =
            Pattern.compile("(generate|create|build|explain|describe|list|provide|write|develop)");

    private static final Pattern CONSTRAINT_PATTERN =
            Pattern.compile("(limit|maximum|minimum|only|must|should|avoid|exclude)");

    // ==============================
    // 1. ANALYZE PROMPT
    // ==============================
    public List<String> analyzePrompt(String prompt) {

        List<String> issues = new ArrayList<>();

        if (prompt == null || prompt.trim().isEmpty()) {
            issues.add("Prompt is empty");
            return issues;
        }

        String lower = prompt.toLowerCase();
        PromptCategory category = detectCategory(prompt);

        if (prompt.length() < 20) {
            issues.add("Prompt is too short - lacks detail");
        }

        if (!ACTION_PATTERN.matcher(lower).find()) {
            issues.add("No clear action verb (generate, create, explain...)");
        }

        if (!CONSTRAINT_PATTERN.matcher(lower).find()) {
            issues.add("No constraints specified");
        }

        if (!containsContext(prompt)) {
            issues.add("Missing context or audience");
        }

        if (!containsOutputFormat(prompt)) {
            issues.add("No output format specified");
        }

        if (category == PromptCategory.UNKNOWN) {
            issues.add("Missing or unclear domain");
        }

        return issues.isEmpty() ? List.of("No major issues detected") : issues;
    }

    // ==============================
    // 2. SCORE
    // ==============================
    public double calculateScore(String prompt, List<String> issues) {

        double score = 100;

        score -= issues.size() * 10;

        if (prompt.length() < 20) score -= 15;
        if (!hasStructure(prompt)) score -= 10;

        return Math.max(0, Math.min(100, score));
    }

    // ==============================
    // 3. IMPROVED PROMPT
    // ==============================
    public String generateImprovedPrompt(String prompt, List<String> issues) {

        String cleaned = prompt == null ? "" : prompt.trim();

        if (cleaned.isEmpty()) {
            return "Provide a detailed prompt specifying task, technology, and requirements.";
        }

        PromptCategory category = detectCategory(cleaned);

        // 🔥 SHORT PROMPT HANDLING (MAIN FIX)
        if (cleaned.length() < 50) {

            switch (category) {

                case GAME:
                    return "Create a Snake Game using JavaScript and HTML Canvas. " +
                            "Include snake movement, food generation, collision detection, and score tracking. " +
                            "Write clean, beginner-friendly code with comments.";

                case WEBSITE:
                    return "Create a responsive website using HTML, CSS, and JavaScript. " +
                            "Include modern UI design, navigation, and interactive elements. " +
                            "Ensure mobile responsiveness and clean code.";

                case API:
                    return "Develop a REST API using Java and Spring Boot. " +
                            "Include CRUD operations, validation, and proper error handling.";

                case CHATBOT:
                    return "Build a chatbot using Python and NLP. " +
                            "Include intent recognition and conversation flow.";

                case DATA:
                    return "Perform data analysis using Python and pandas. " +
                            "Include cleaning, visualization, and insights.";

                default:
                    return "Provide a detailed prompt including task, technology, constraints, and output format.";
            }
        }

        // LONG PROMPT STRUCTURE
        String role = CATEGORY_ROLES.getOrDefault(category, "Software Developer");

        return "# Role\n" + role +
                "\n\n# Task\nImprove the given prompt with clarity and structure." +
                "\n\n# Constraints\nEnsure clarity and avoid ambiguity." +
                "\n\n# Output Format\nStructured response";
    }

    // ==============================
    // 4. AGENT.MD
    // ==============================
    public String generateAgentMd(String prompt, List<String> issues) {

        PromptCategory category = detectCategory(prompt);
        String role = CATEGORY_ROLES.getOrDefault(category, "Software Developer");

        return "# Role\n" + role + "\n\n" +
                "# Task\nImprove and execute the prompt.\n\n" +
                "# Requirements\n- Clear\n- Structured\n\n" +
                "# Output\nProvide complete solution.";
    }

    // ==============================
    // HELPER METHODS
    // ==============================

    private PromptCategory detectCategory(String prompt) {
        String lower = prompt.toLowerCase();
        if (lower.contains("game")) return PromptCategory.GAME;
        if (lower.contains("website")) return PromptCategory.WEBSITE;
        if (lower.contains("api")) return PromptCategory.API;
        if (lower.contains("chatbot")) return PromptCategory.CHATBOT;
        if (lower.contains("data")) return PromptCategory.DATA;
        return PromptCategory.UNKNOWN;
    }

    private boolean containsContext(String prompt) {
        return prompt.toLowerCase().contains("for");
    }

    private boolean containsOutputFormat(String prompt) {
        return prompt.toLowerCase().contains("json") ||
                prompt.toLowerCase().contains("list") ||
                prompt.toLowerCase().contains("steps");
    }

    private boolean hasStructure(String prompt) {
        return prompt.contains(".") || prompt.contains(",");
    }
}