package com.promptoptimizer.model;

import jakarta.persistence.*;

@Entity
public class PromptHistory {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private String prompt;

@Column(length=5000)
private String response;

private int optimizedTokens;

private double savedCost;

@ManyToOne
@JoinColumn(name="user_id")
private User user;

public String getPrompt(){return prompt;}
public int getOptimizedTokens(){return optimizedTokens;}

public void setPrompt(String prompt){this.prompt=prompt;}
public void setResponse(String response){this.response=response;}
public void setOptimizedTokens(int t){optimizedTokens=t;}
public void setSavedCost(double c){savedCost=c;}
public void setUser(User u){user=u;}
}
