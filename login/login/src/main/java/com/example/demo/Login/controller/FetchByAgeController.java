//package com.example.demo.Login.controller;
//
//import com.example.demo.Login.model.User;
//import com.example.demo.Login.service.FetchByAgeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/auth")
//public class FetchByAgeController {
//
//    @Autowired
//    private FetchByAgeService fetchByAgeService;
//
//    @GetMapping("/age")
//    public ResponseEntity<?> getUsersByAgeRange(
//            @RequestParam int minAge,
//            @RequestParam int maxAge) {
//
//        List<User> users = fetchByAgeService.getUsersByAgeRange(minAge, maxAge);
//
//        if (users.isEmpty()) {
//            return ResponseEntity.status(404).body("No users found in this age range");
//        }
//
//        return ResponseEntity.ok(users);
//    }
//}
