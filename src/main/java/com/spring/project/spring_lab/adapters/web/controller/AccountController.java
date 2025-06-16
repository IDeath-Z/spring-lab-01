package com.spring.project.spring_lab.adapters.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.spring_lab.adapters.web.dto.account.AccountRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.account.AccountResponseDTO;
import com.spring.project.spring_lab.adapters.web.dto.account.EmailRequestDTO;
import com.spring.project.spring_lab.application.services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(request));
    }

    @PostMapping("/getByEmail")
    public ResponseEntity<?> getAccount(@Valid @RequestBody EmailRequestDTO request) {

        return ResponseEntity.ok(accountService.fetchAccount(request.email()));
    }

}
