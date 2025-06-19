package com.spring.project.spring_lab.domain.exceptions.wallet;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(UUID id) {

        super("Wallet not found with id: " + id);
    }

    public WalletNotFoundException(String accountEmail) {

        super("No wallet found associated with email: " + accountEmail);
    }
}
