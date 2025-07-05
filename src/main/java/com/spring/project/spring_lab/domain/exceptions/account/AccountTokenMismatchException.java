package com.spring.project.spring_lab.domain.exceptions.account;

public class AccountTokenMismatchException extends RuntimeException {

    public AccountTokenMismatchException() {

        super("Access denied: you can only perform actions on your own account.");
    }

}
