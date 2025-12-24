package com.example.demo.Login.controller;

import com.example.demo.Login.model.User;
import com.example.demo.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserListController {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get N users dynamically
    @GetMapping("/user/{count}")
    public List<User> getLimitedUsers(@PathVariable int count) {
        List<User> all = userRepository.findAll();
        return all.stream().limit(count).toList();
    }

    // DELETE user by ID
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
