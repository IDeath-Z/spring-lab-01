package com.spring.project.spring_lab.application.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.project.spring_lab.adapters.web.dto.account.AccountRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.account.AccountResponseDTO;
import com.spring.project.spring_lab.domain.Account;

@Component
public class AccountMapper {

    @Autowired
    WalletMapper walletMapper;

    public Account toDomain(AccountRequestDTO request) {

        Account account = new Account();
        account.setFullname(request.fullName());
        account.setTaxId(request.taxId());
        account.setEmail(request.email());
        account.setRole(request.role());

        return account;
    }

    public AccountResponseDTO toResponseDTO(Account account) {

        return new AccountResponseDTO(
                account.getId(),
                account.getFullname(),
                account.getEmail(),
                account.getRole(),
                walletMapper.toResponseDTO(account.getMainWallet()));
    }
}
