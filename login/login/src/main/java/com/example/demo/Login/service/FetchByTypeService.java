//package com.example.demo.Login.service;
//
//import com.example.demo.Login.model.User;
//import com.example.demo.Login.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FetchByTypeService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Assign type based on age and return updated list
//    public List<User> assignUserType() {
//
//        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//
//            if (user.getAge() < 18) {
//                user.setType("kid");
//            } else {
//                user.setType("parent");
//            }
//
//            userRepository.save(user); // update each document
//        }
//
//        return users;
//    }
//}
