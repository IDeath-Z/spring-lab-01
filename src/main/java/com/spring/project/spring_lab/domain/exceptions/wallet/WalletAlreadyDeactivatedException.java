package com.spring.project.spring_lab.domain.exceptions.wallet;

public class WalletAlreadyDeactivatedException extends RuntimeException {

    public WalletAlreadyDeactivatedException() {

        super("The wallet is already deactivated.");
    }

}
