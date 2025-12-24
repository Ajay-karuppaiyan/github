package com.example.demo.Login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Login.model.User;
import com.example.demo.Login.repository.UserRepository;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signup(User user) {

        // ********* VALIDATION START *********
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return "Name is required!";
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "Email is required!";
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password is required!";
        }
        // ********* VALIDATION END *********

        // Check for duplicate email
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists!";
        }

        // ✅ SET ROLE PROPERLY
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER"); // default role
        } else {
            user.setRole(user.getRole().toUpperCase()); // ADMIN / USER
        }

        // HASH PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // SAVE USER
        userRepository.save(user);

        return "User created successfully!";
    }
}
