package com.promptoptimizer.dto;

import java.util.Objects;

public class IssueDto {

    private final String type;
    private final String detail;
    private final String recommendation;

    public IssueDto(String type, String detail, String recommendation) {
        this.type = type;
        this.detail = detail;
        this.recommendation = recommendation;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public String getRecommendation() {
        return recommendation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueDto issueDto = (IssueDto) o;
        return Objects.equals(type, issueDto.type) && Objects.equals(detail, issueDto.detail) && Objects.equals(recommendation, issueDto.recommendation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, detail, recommendation);
    }
}
