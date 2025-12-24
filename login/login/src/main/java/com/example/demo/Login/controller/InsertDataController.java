//package com.example.demo.Login.controller;
//
//import com.example.demo.Login.model.User;
//import com.example.demo.Login.service.InsertDataService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class InsertDataController {
//
//    @Autowired
//    private InsertDataService insertDataService;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        User savedUser = insertDataService.createUser(user);
//        return ResponseEntity.status(201).body(savedUser);
//    }
//
//    @PostMapping ("/insert/{id}")
//    public ResponseEntity<?> insertDataById(@PathVariable String id, @RequestBody User updatedData) {
//        User updatedUser = insertDataService.insertById(id, updatedData);
//        if (updatedUser == null) {
//            return ResponseEntity.status(404).body("User with id " + id + " not found");
//        }
//        return ResponseEntity.ok(updatedUser);
//    }
//}