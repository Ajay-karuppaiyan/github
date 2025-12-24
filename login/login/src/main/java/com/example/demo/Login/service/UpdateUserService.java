package com.example.demo.Login.service;

import com.example.demo.Login.model.User;
import com.example.demo.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(String id, User newData) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Name
        if (newData.getName() != null && !newData.getName().trim().isEmpty()) {
            existingUser.setName(newData.getName().trim());
        }

        // Email (only if changed)
        if (newData.getEmail() != null &&
                !newData.getEmail().trim().equals(existingUser.getEmail())) {

            if (userRepository.existsByEmail(newData.getEmail().trim())) {
                throw new RuntimeException("Email already exists");
            }
            existingUser.setEmail(newData.getEmail().trim());
        }

        // Mobile (only if changed)
        if (newData.getMobile() != null &&
                !newData.getMobile().trim().equals(existingUser.getMobile())) {

            if (userRepository.existsByMobile(newData.getMobile().trim())) {
                throw new RuntimeException("Mobile number already exists");
            }
            existingUser.setMobile(newData.getMobile().trim());
        }

        // Age
        if (newData.getAge() != null) {
            existingUser.setAge(newData.getAge());
        }

        // ❌ DO NOT UPDATE PASSWORD HERE

        return userRepository.save(existingUser);
    }
}
