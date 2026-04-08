package com.promptoptimizer.repository;

import com.promptoptimizer.model.PromptHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptHistoryRepository extends JpaRepository<PromptHistory, Long> {
}
