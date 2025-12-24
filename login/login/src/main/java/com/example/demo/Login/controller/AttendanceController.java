package com.example.demo.Login.controller;

import com.example.demo.Login.model.User;
import com.example.demo.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

    @Autowired
    private UserRepository userRepository;

    // ---------------- Mark Attendance ----------------
    @PostMapping("/attendance/{userId}")
    public Map<String, Object> markAttendance(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            response.put("status", 404);
            response.put("message", "User not found");
            return response;
        }

        String today = LocalDate.now().toString();

        if (user.getAttendance().contains(today)) {
            response.put("status", 400);
            response.put("message", "Attendance already marked for today");
            return response;
        }

        user.addAttendance(today);
        userRepository.save(user);

        response.put("status", 200);
        response.put("message", "Attendance marked for " + today);
        return response;
    }

    // ---------------- Get Attendance ----------------
    @GetMapping("/attendance/{userId}")
    public List<String> getAttendance(
            @PathVariable String userId,
            @RequestParam(required = false) String month // optional filter
    ) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return new ArrayList<>();

        List<String> attendance = user.getAttendance();
        if (month != null) { // filter by month (format: YYYY-MM)
            attendance = attendance.stream()
                    .filter(date -> date.startsWith(month))
                    .collect(Collectors.toList());
        }
        return attendance;
    }

    // ---------------- Get All Users Attendance ----------------
    @GetMapping("/attendance")
    public List<Map<String, Object>> getAllUsersAttendance() {
        List<User> users = userRepository.findByRole("user");
        List<Map<String, Object>> result = new ArrayList<>();
        for (User u : users) {
            Map<String, Object> m = new HashMap<>();
            m.put("id", u.getId());
            m.put("name", u.getName());
            m.put("email", u.getEmail());
            m.put("attendance", u.getAttendance());
            result.add(m);
        }
        return result;
    }
}
