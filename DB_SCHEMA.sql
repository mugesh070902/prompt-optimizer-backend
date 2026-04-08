CREATE DATABASE IF NOT EXISTS prompt_optimizer;
USE prompt_optimizer;

CREATE TABLE IF NOT EXISTS prompt_history (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  original_prompt TEXT,
  improved_prompt TEXT,
  agent_md TEXT,
  issues TEXT,
  score_clarity INT,
  score_efficiency INT,
  score_structure INT,
  token_count_before INT,
  token_count_after INT,
  token_reduction INT,
  created_at DATETIME
);
