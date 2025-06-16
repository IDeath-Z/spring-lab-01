package com.spring.project.spring_lab.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.spring_lab.adapters.web.dto.account.AccountRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.account.AccountResponseDTO;
import com.spring.project.spring_lab.application.mappers.AccountMapper;
import com.spring.project.spring_lab.domain.Account;
import com.spring.project.spring_lab.domain.exceptions.CnpjAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.CpfAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.AccountAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.AccountNotFoundException;
import com.spring.project.spring_lab.infrastructure.persistence.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

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
        return accountMapper.toResponeDTO(accountRepository.save(account));
    }

    public AccountResponseDTO fetchAccount(UUID id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        return accountMapper.toResponeDTO(account);
    }

    public AccountResponseDTO fetchAccount(String email) {

        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new AccountNotFoundException(email));

        return accountMapper.toResponeDTO(account);
    }

}
