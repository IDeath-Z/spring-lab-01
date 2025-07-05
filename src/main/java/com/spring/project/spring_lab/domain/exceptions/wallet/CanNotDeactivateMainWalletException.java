package com.spring.project.spring_lab.domain.exceptions.wallet;

public class CanNotDeactivateMainWalletException extends RuntimeException {

    public CanNotDeactivateMainWalletException() {

        super("The main wallet cannot be deactivated.");
    }

}
