package com.promptoptimizer.repository;

import com.promptoptimizer.model.PromptHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptHistoryRepository extends JpaRepository<PromptHistory, Long> {
}
