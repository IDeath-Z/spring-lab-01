package com.spring.project.spring_lab.domain.exceptions.account;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(UUID id) {

        super("Account not found with id: " + id);
    }

    public AccountNotFoundException(String email) {

        super("Account not found with e-mail: " + email);
    }
}
