package com.example.demo.Login.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Getter
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String mobile;
    private Integer age;
    private String role;

    // --- New: Attendance List ---
    private List<String> attendance = new ArrayList<>(); // store dates as YYYY-MM-DD

    // --- Getters ---
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getMobile() { return mobile; }
    public Integer getAge() { return age; }
    public String getRole() { return role; }
    public List<String> getAttendance() { return attendance; }

    // --- Setters ---
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setAge(Integer age) { this.age = age; }
    public void setRole(String role) { this.role = role; }
    public void setAttendance(List<String> attendance) { this.attendance = attendance; }

    // --- Helper Method ---
    public void addAttendance(String date) {
        if (!this.attendance.contains(date)) { // prevent duplicate entries
            this.attendance.add(date);
        }
    }
}
