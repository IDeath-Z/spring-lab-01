package com.spring.project.spring_lab.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionType {

    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    TRANSFER("TRANSFER");

    private final String Transactions;

    TransactionType(String Transactions) {
        this.Transactions = Transactions;
    }

}
