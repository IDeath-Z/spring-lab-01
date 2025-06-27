package com.spring.project.spring_lab.domain.exceptions.account;

import java.util.UUID;

public class AccountAlreadyDeactivatedException extends RuntimeException {

    public AccountAlreadyDeactivatedException(UUID id) {

        super("Account already deactivated with id: " + id);
    }

    public AccountAlreadyDeactivatedException(String email) {

        super("Account already deactivated with e-mail: " + email);
    }
}
