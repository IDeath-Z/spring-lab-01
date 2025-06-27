package com.spring.project.spring_lab.adapters.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.spring_lab.application.services.WalletService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/create/{accountId}")
    public ResponseEntity<?> createNewWallet(@PathVariable UUID accountId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.addWallet(accountId));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<?> getWalletById(@PathVariable UUID walletId) {

        return ResponseEntity.status(HttpStatus.OK).body(walletService.fetchById(walletId));
    }

    @GetMapping("/all/{accountId}")
    public ResponseEntity<?> getAllWalletsByAccountId(@PathVariable UUID accountId,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(walletService.fetchAllByAccountId(accountId, Pageable.ofSize(size).withPage(page)));
    }
}
