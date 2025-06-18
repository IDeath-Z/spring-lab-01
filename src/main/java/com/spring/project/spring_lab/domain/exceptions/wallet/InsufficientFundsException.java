package com.spring.project.spring_lab.domain.exceptions.wallet;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {

        super("This wallet was insufficient money for this transaction");
    }
}
