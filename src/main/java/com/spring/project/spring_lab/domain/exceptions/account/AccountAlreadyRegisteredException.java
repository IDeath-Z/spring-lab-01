package com.spring.project.spring_lab.domain.exceptions.account;

public class AccountAlreadyRegisteredException extends RuntimeException {

    public AccountAlreadyRegisteredException(String email) {

        super("An account is already registered with this email: " + email);
    }
}
