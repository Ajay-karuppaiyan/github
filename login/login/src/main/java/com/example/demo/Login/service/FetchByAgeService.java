//package com.example.demo.Login.service;
//
//import com.example.demo.Login.model.User;
//import com.example.demo.Login.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FetchByAgeService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Fetch all users within age range
//    public List<User> getUsersByAgeRange(int minAge, int maxAge) {
//
//        List<User> allUsers = userRepository.findAll();
//
//        return allUsers.stream()
//                .filter(u -> u.getAge() >= minAge && u.getAge() <= maxAge)
//                .collect(Collectors.toList());
//    }
//}
