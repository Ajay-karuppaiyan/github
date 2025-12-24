package com.example.demo.Login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.Login.model.User;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);           // For login
    boolean existsByEmail(String email);      // For signup
    boolean existsByMobile(String mobile);

    List<User> findByAgeBetween(int minAge, int maxAge);
    List<User> findByRole(String role); // for admin list

    long countByRole(String role);

    @Query(value = "{ attendance: ?0 }", count = true)
    long countUsersByAttendance(String date);
}
