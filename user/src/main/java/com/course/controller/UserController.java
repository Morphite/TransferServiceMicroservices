package com.course.controller;

import com.course.model.User;
import com.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createNewUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}