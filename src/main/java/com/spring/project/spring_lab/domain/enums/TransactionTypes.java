package com.spring.project.spring_lab.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionTypes {

    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    TRANSFER("TRANSFER");

    private final String Transactions;

    TransactionTypes(String Transactions) {
        this.Transactions = Transactions;
    }

}
