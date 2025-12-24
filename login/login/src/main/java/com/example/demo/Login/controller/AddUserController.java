package com.example.demo.Login.controller;

import com.example.demo.Login.model.User;
import com.example.demo.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/auth")
public class AddUserController {

    @Autowired
    private UserRepository userRepository;

//     ---------------- SIGNUP ----------------
//    @PostMapping("/signup")
//    public HashMap<String, Object> signup(@RequestBody User user) {
//
//        HashMap<String, Object> response = new HashMap<>();
//
//        User existing = userRepository.findByEmail(user.getEmail());
//
//        if (existing != null) {
//            response.put("status", 400);
//            response.put("message", "Email already exists");
//            return response;
//        }
//
//        if (user.getRole() == null) {
//            user.setRole("USER"); // default role
//        }
//
//        userRepository.save(user);
//
//        response.put("status", 200);
//        response.put("message", "Signup successful");
//        return response;
//    }
//
//     ---------------- LOGIN ----------------
//    @PostMapping("/login")
//    public HashMap<String, Object> login(@RequestBody User input) {
//
//        HashMap<String, Object> response = new HashMap<>();
//
//        User dbUser = userRepository.findByEmail(input.getEmail());
//
//        if (dbUser == null) {
//            response.put("status", 404);
//            response.put("message", "Email not registered");
//            return response;
//        }
//
//        if (!dbUser.getPassword().equals(input.getPassword())) {
//            response.put("status", 401);
//            response.put("message", "Incorrect password");
//            return response;
//        }
//
//        response.put("status", 200);
//        response.put("message", "Login Successful");
//        response.put("id", dbUser.getId());
//        response.put("name", dbUser.getName());
//        response.put("email", dbUser.getEmail());
//        response.put("role", dbUser.getRole());
//
//        return response;
//    }

    // ---------------- VIEW USERS ----------------
    @GetMapping("/Users")
    public List<User> getUsers() {
        return userRepository.findByRole("user");
    }

    // ---------------- ADD USER ----------------
    @PostMapping("/add-user")
    public HashMap<String, Object> addUser(@RequestBody User user) {

        user.setRole("user");

        userRepository.save(user);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "User added successfully");

        return response;
    }

    // ---------------- VIEW ADMINS ----------------
    @GetMapping("/admins")
    public List<User> getAdmins() {
        return userRepository.findByRole("admin");
    }

    // ---------------- ADD ADMIN ----------------
    @PostMapping("/add-admin")
    public HashMap<String, Object> addAdmin(@RequestBody User admin) {

        admin.setRole("admin");

        userRepository.save(admin);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Admin added successfully");

        return response;
    }
}
