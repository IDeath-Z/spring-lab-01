package com.spring.project.spring_lab.application.mappers;

import org.springframework.stereotype.Component;

import com.spring.project.spring_lab.adapters.web.dto.transaction.TransactionResponseDTO;
import com.spring.project.spring_lab.domain.Transaction;

@Component
public class TransactionMapper {

    public TransactionResponseDTO toResponseDTO(Transaction transaction) {

        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getSenderWallet() == null ? null
                        : transaction.getSenderWallet().getAccount().getFullname(),
                transaction.getReceiverWallet().getAccount().getFullname(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDate());
    }
}
