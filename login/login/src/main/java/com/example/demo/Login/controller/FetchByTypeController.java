//package com.example.demo.Login.controller;
//
//import com.example.demo.Login.model.User;
//import com.example.demo.Login.service.FetchByTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/auth")
//public class FetchByTypeController {
//
//    @Autowired
//    private FetchByTypeService fetchByTypeService;
//
//    @GetMapping("/type")
//    public ResponseEntity<?> updateUserTypes() {
//
//        List<User> updated = fetchByTypeService.assignUserType();
//
//        return ResponseEntity.ok(updated);
//    }
//}
