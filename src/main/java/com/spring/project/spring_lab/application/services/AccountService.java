package com.spring.project.spring_lab.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.project.spring_lab.adapters.web.dto.account.AccountRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.account.AccountResponseDTO;
import com.spring.project.spring_lab.application.mappers.AccountMapper;
import com.spring.project.spring_lab.domain.Account;
import com.spring.project.spring_lab.domain.Wallet;
import com.spring.project.spring_lab.domain.exceptions.account.AccountAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.account.AccountNotFoundException;
import com.spring.project.spring_lab.domain.exceptions.account.CnpjAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.account.CpfAlreadyRegisteredException;
import com.spring.project.spring_lab.infrastructure.persistence.AccountRepository;
import com.spring.project.spring_lab.infrastructure.persistence.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public AccountResponseDTO addAccount(AccountRequestDTO request) {

        if (accountRepository.existsByEmail(request.email())) {

            throw new AccountAlreadyRegisteredException(request.email());
        }

        if (accountRepository.existsByTaxId(request.taxId())) {
            if (request.isJuridicPerson()) {

                throw new CnpjAlreadyRegisteredException(request.taxId());
            } else {

                throw new CpfAlreadyRegisteredException(request.taxId());
            }
        }

        Account account = accountMapper.toDomain(request);
        account.setPassword(passwordEncoder.encode(request.password()));

        Wallet wallet = new Wallet(account, 0.0);
        account.getWallets().add(wallet);

        account.setMainWallet(wallet);

        return accountMapper.toResponseDTO(accountRepository.save(account));
    }

    public AccountResponseDTO fetchById(UUID accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        return accountMapper.toResponseDTO(account);
    }

    public AccountResponseDTO fetchByEmail(String email) {

        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new AccountNotFoundException(email));

        return accountMapper.toResponseDTO(account);
    }

}
