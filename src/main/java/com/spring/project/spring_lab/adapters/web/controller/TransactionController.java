package com.spring.project.spring_lab.adapters.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.spring_lab.adapters.web.dto.transaction.DepositRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.transaction.TransferRequestDTO;
import com.spring.project.spring_lab.application.services.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionsService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionsService.deposit(request));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequestDTO request, @AuthenticationPrincipal Jwt jwt) {

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionsService.transfer(request));
    }
}
