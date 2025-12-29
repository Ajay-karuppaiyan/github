package com.project.Hospital.Controller;

import com.project.Hospital.Model.User;
import com.project.Hospital.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) throws Exception {
        return authService.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = authService.login(user.getEmail(), user.getPassword());

            User dbUser = authService.getUserByEmail(user.getEmail());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", dbUser.getRole()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
