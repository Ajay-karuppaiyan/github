//package com.example.demo.Login.service;
//
//import com.example.demo.Login.model.User;
//import com.example.demo.Login.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InsertDataService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // CREATE NEW USER
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    // UPDATE EXISTING USER BY ID
//    public User insertById(String id, User updatedData) {
//        return userRepository.findById(id)
//                .map(existingUser -> {
//                    if (updatedData.getName() != null)
//                        existingUser.setName(updatedData.getName());
//                    if (updatedData.getEmail() != null)
//                        existingUser.setEmail(updatedData.getEmail());
//                    if (updatedData.getMobile() != null)
//                        existingUser.setMobile(updatedData.getMobile());
//                    if (updatedData.getPassword() != null)
//                        existingUser.setPassword(updatedData.getPassword());
//
//                    return userRepository.save(existingUser);
//                })
//                .orElse(null);
//    }
//}
