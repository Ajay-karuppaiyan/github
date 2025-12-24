//package com.example.demo.Login.controller;
//
//import com.example.demo.Login.service.UpdateAllDocumentsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.bson.Document;
//
//@RestController
//@RequestMapping("/api/auth/update")
//public class UpdateAllDocumentsController {
//
//    @Autowired
//    private UpdateAllDocumentsService updateAllDocumentsService;
//
//    @PostMapping("/all")
//    public ResponseEntity<?> updateAll(@RequestBody Document inputData) {
//
//        int updatedCount = updateAllDocumentsService.updateAllCollections(inputData);
//
//       // return ResponseEntity.ok(updatedCount + " documents Updated.");
//        return ResponseEntity.ok("All documents updated successfully.");
//    }
//}
