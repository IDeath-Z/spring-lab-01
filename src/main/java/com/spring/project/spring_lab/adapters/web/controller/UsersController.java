package com.spring.project.spring_lab.adapters.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.spring_lab.adapters.web.dto.users.EmailRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.users.UserRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.users.UserResponseDTO;
import com.spring.project.spring_lab.application.services.UsersService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.addUser(request));
    }

    @PostMapping("/getByEmail")
    public ResponseEntity<?> getUser(@Valid @RequestBody EmailRequestDTO request) {

        return ResponseEntity.ok(usersService.fetchUser(request.email()));
    }

}
