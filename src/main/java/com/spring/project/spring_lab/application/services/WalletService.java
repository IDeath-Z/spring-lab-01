package com.spring.project.spring_lab.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.spring_lab.adapters.web.dto.wallet.WalletResponseDTO;
import com.spring.project.spring_lab.application.mappers.WalletMapper;
import com.spring.project.spring_lab.domain.Account;
import com.spring.project.spring_lab.domain.Wallet;
import com.spring.project.spring_lab.domain.exceptions.AccountNotFoundException;
import com.spring.project.spring_lab.infrastructure.persistence.AccountRepository;
import com.spring.project.spring_lab.infrastructure.persistence.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private AccountRepository accountRepository;

    public WalletResponseDTO addWallet(UUID accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        Wallet wallet = new Wallet(account);
        return walletMapper.toResponseDTO(walletRepository.save(wallet));
    }

}
