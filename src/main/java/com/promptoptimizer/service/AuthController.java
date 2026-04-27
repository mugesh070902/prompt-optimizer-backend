package com.promptoptimizer.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;

import com.promptoptimizer.model.User;
import com.promptoptimizer.service.AuthService;
import com.promptoptimizer.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

private final AuthService service;

public AuthController(AuthService service){
this.service=service;
}

@PostMapping("/signup")
public String signup(
@RequestBody User user){

return service.signup(user);

}

@PostMapping("/login")
public Map<String,Object> login(
@RequestBody Map<String,String> req){

User user=
service.login(
req.get("email"),
req.get("password"));

Map<String,Object> res=
new HashMap<>();

if(user!=null){

res.put(
"token",
JwtUtil.generateToken(
user.getEmail()));

res.put(
"name",
user.getName());

return res;
}

res.put("error","Invalid");

return res;
}
}
