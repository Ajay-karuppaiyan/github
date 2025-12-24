package com.example.demo.Login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Login.model.User;
import com.example.demo.Login.service.SignupService;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {

        String savedUser = signupService.signup(user);

        if (savedUser == null) {
            return ResponseEntity.status(409).body("Email already exists");
        }

        return ResponseEntity.status(201).body(savedUser);
    }
}