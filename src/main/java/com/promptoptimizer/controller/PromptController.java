package com.promptoptimizer.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.promptoptimizer.service.*;
import com.promptoptimizer.repository.*;
import com.promptoptimizer.model.*;
import com.promptoptimizer.security.JwtFilter;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PromptController {

private final OptimizedAIService service;
private final UserRepository userRepo;
private final PromptHistoryRepository repo;

public PromptController(
OptimizedAIService service,
UserRepository userRepo,
PromptHistoryRepository repo){

this.service=service;
this.userRepo=userRepo;
this.repo=repo;
}

@GetMapping("/")
public String home(){
return "API Working";
}

@PostMapping("/analyze")
public Map<String,Object> analyze(
@RequestBody Map<String,String> req){

return service.process(req);
}

@GetMapping("/history")
public List<PromptHistory> history(){

User user=
userRepo.findByEmail(
JwtFilter.currentUserEmail)
.orElse(null);

return repo.findByUser(user);
}
}
