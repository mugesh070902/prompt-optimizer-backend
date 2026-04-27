package com.promptoptimizer.service;

import com.promptoptimizer.model.User;
import com.promptoptimizer.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.
BCryptPasswordEncoder;

@Service
public class AuthService {

private final UserRepository repo;

private BCryptPasswordEncoder encoder=
new BCryptPasswordEncoder();

public AuthService(UserRepository repo){
this.repo=repo;
}

public String signup(User user){

user.setPassword(
encoder.encode(user.getPassword()));

repo.save(user);

return "Registered";
}

public User login(
String email,
String password){

User user=
repo.findByEmail(email).orElse(null);

if(user!=null &&
encoder.matches(
password,
user.getPassword())){

return user;
}

return null;
}
}
