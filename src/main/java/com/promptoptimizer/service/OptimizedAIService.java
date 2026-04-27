package com.promptoptimizer.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.promptoptimizer.model.*;
import com.promptoptimizer.repository.*;
import com.promptoptimizer.security.JwtFilter;

@Service
public class OptimizedAIService {

private final PromptHistoryRepository repo;
private final UserRepository userRepo;

public OptimizedAIService(
PromptHistoryRepository repo,
UserRepository userRepo){

this.repo=repo;
this.userRepo=userRepo;
}

public Map<String,Object> process(
Map<String,String> input){

String desc=input.get("desc");
String front=input.get("frontend");
String back=input.get("backend");
String db=input.get("database");

String prompt=
desc+" | "+
front+" "+
back+" "+
db;

String agent=
"# AI Agent Instructions\n"+
"Project:"+desc;

int tokens=
prompt.length()/4;

double cost=
(tokens/1000.0)*0.002;

User user=
userRepo.findByEmail(
JwtFilter.currentUserEmail)
.orElse(null);

PromptHistory h=
new PromptHistory();

h.setPrompt(prompt);
h.setResponse(prompt);
h.setOptimizedTokens(tokens);
h.setSavedCost(cost);
h.setUser(user);

repo.save(h);

Map<String,Object> map=
new HashMap<>();

map.put(
"improvedPrompt",
prompt);

map.put(
"agentMd",
agent);

map.put(
"savedTokens",
tokens);

map.put(
"savedCost",
cost);

return map;
}
}
