package com.project.Hospital.Controller;

import com.project.Hospital.Model.User;
import com.project.Hospital.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // your frontend URL
public class AuthController {

    @Autowired
    private AuthService authService;

    // Signup
    @PostMapping("/signup")
    public User signup(@RequestBody User user) throws Exception {
        return authService.signup(user);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = authService.login(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(token); // returns JWT as string
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

}
