package com.spring.project.spring_lab.domain.exceptions.wallet;

public class BalanceMustBeZeroException extends RuntimeException {

    public BalanceMustBeZeroException() {

        super("The wallet balance must be zero to perform this operation.");
    }
}
