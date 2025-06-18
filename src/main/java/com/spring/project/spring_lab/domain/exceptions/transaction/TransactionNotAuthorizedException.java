package com.spring.project.spring_lab.domain.exceptions.transaction;

public class TransactionNotAuthorizedException extends RuntimeException {

    public TransactionNotAuthorizedException() {

        super("Transaction denied by external authorization service");
    }
}
