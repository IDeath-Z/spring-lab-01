package com.spring.project.spring_lab.adapters.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.spring_lab.adapters.web.dto.account.AccountUuidRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.wallet.WalletResponseDTO;
import com.spring.project.spring_lab.application.services.WalletService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/register")
    public ResponseEntity<WalletResponseDTO> createNewWallet(@Valid @RequestBody AccountUuidRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.addWallet(request.accountId()));
    }

}
