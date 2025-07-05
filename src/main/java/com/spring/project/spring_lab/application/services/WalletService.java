package com.spring.project.spring_lab.application.services;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.project.spring_lab.adapters.web.dto.wallet.WalletPageResponseDTO;
import com.spring.project.spring_lab.adapters.web.dto.wallet.WalletResponseDTO;
import com.spring.project.spring_lab.application.mappers.WalletMapper;
import com.spring.project.spring_lab.domain.Account;
import com.spring.project.spring_lab.domain.Wallet;
import com.spring.project.spring_lab.domain.exceptions.account.AccountNotFoundException;
import com.spring.project.spring_lab.domain.exceptions.account.AccountTokenMismatchException;
import com.spring.project.spring_lab.domain.exceptions.wallet.BalanceMustBeZeroException;
import com.spring.project.spring_lab.domain.exceptions.wallet.CanNotDeactivateMainWalletException;
import com.spring.project.spring_lab.domain.exceptions.wallet.WalletAlreadyDeactivatedException;
import com.spring.project.spring_lab.domain.exceptions.wallet.WalletNotFoundException;
import com.spring.project.spring_lab.infrastructure.persistence.AccountRepository;
import com.spring.project.spring_lab.infrastructure.persistence.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public WalletResponseDTO addWallet(UUID accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        if (!tokenService.isTokenValid(account)) {

            throw new AccountTokenMismatchException();
        }

        Wallet walletToUse = null;

        for (Wallet wallet : account.getWallets()) {

            if (!wallet.isActive()) {

                walletToUse = wallet;
                wallet.setActive(true);
                break;
            }
        }

        if (walletToUse == null) {
            walletToUse = new Wallet(account, 0.0);
        }

        return walletMapper.toResponseDTO(walletRepository.save(walletToUse));
    }

    public WalletResponseDTO fetchById(UUID walletId) {

        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException(walletId));

        return walletMapper.toResponseDTO(wallet);
    }

    public WalletPageResponseDTO fetchAllByAccountId(UUID accountId, Pageable pageable) {

        if (!accountRepository.existsById(accountId)) {
            throw new AccountNotFoundException(accountId);
        }

        Page<Wallet> walletsPage = walletRepository.findAllByAccountId(accountId, pageable);

        return new WalletPageResponseDTO(
                walletsPage.getTotalPages(),
                walletsPage.getContent().stream()
                        .map(walletMapper::toResponseDTO)
                        .collect(Collectors.toList()));
    }

    @Transactional
    public void deactivateWalletById(UUID walletId) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));

        if (!tokenService.isTokenValid(wallet.getAccount())) {

            throw new AccountTokenMismatchException();
        }

        if (wallet.getAccount().getMainWallet().equals(wallet)) {

            throw new CanNotDeactivateMainWalletException();
        }

        if (!wallet.isActive()) {

            throw new WalletAlreadyDeactivatedException();
        }

        if (wallet.getBalance() != 0.0) {

            throw new BalanceMustBeZeroException();
        }

        wallet.setActive(false);
        walletRepository.save(wallet);
    }

}
