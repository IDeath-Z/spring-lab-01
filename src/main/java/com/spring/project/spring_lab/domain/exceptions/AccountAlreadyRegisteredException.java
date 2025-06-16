package com.spring.project.spring_lab.domain.exceptions;

public class AccountAlreadyRegisteredException extends RuntimeException {

    public AccountAlreadyRegisteredException(String email) {

        super("An account is already registered with this email: " + email);
    }
}
