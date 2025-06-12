package com.spring.project.spring_lab.domain.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String email) {

        super("A user is already registered with this email: " + email);
    }
}
