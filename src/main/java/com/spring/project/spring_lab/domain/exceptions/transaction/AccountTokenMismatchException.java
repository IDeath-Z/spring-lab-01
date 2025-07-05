package com.spring.project.spring_lab.domain.exceptions.transaction;

public class AccountTokenMismatchException extends RuntimeException {

    public AccountTokenMismatchException() {

        super("Access denied: you can only perform transactions on your own account.");
    }

}
