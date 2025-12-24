package com.example.demo.Login.controller;

import com.example.demo.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/auth/user")
public class UserDeleteController {

    @Autowired
    private UserRepository userRepository;

    // DELETE user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(404).body("User not found with ID: " + id);
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}