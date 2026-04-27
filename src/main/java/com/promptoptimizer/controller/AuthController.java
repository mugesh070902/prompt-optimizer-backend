package com.promptoptimizer.controller;

import com.promptoptimizer.model.User;
import com.promptoptimizer.service.AuthService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return service.signup(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> req) {

        User user = service.login(req.get("email"), req.get("password"));

        if (user != null) {
            return user;
        }
        return "Invalid credentials";
    }
}
