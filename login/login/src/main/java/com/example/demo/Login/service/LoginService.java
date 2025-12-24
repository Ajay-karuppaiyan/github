package com.example.demo.Login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Login.model.User;
import com.example.demo.Login.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(User loginUser) {

        // 1️⃣ Find user by email
        User dbUser = userRepository.findByEmail(loginUser.getEmail());

        if (dbUser == null) {
            return null; // email not registered
        }

        // 2️⃣ Compare BCrypt password
        if (!passwordEncoder.matches(
                loginUser.getPassword(),
                dbUser.getPassword())) {
            return null; // password wrong
        }

        // 3️⃣ Success
        return dbUser;
    }
}
