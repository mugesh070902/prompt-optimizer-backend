package com.promptoptimizer.repository;

import com.promptoptimizer.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PromptHistoryRepository
extends JpaRepository<PromptHistory,Long>{

List<PromptHistory> findByUser(User user);

}
