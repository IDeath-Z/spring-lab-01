package com.spring.project.spring_lab.adapters.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.spring_lab.adapters.web.dto.account.AccountRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.account.AccountResponseDTO;
import com.spring.project.spring_lab.application.services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(request));
    }

    @GetMapping("/by-email")
    public ResponseEntity<?> getAccount(@Valid @RequestParam("email") String email) {

        return ResponseEntity.ok(accountService.fetchByEmail(email));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable UUID accountId) {

        accountService.deactivateAccountById(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
