package com.example.demo.Login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Login.model.User;
import com.example.demo.Login.service.LoginService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User dbUser = loginService.login(user);

        if (dbUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        // ✅ Success response
        Map<String, Object> res = new HashMap<>();
        res.put("message", "Login successful");
        res.put("id", dbUser.getId());
        res.put("name", dbUser.getName());
        res.put("email", dbUser.getEmail());
        res.put("mobile", dbUser.getMobile());
        res.put("age", dbUser.getAge());
        res.put("role", dbUser.getRole());

        return ResponseEntity.ok(res);
    }
}
