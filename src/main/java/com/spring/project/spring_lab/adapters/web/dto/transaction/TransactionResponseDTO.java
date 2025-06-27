package com.spring.project.spring_lab.adapters.web.dto.transaction;

import java.time.LocalDateTime;
import java.util.UUID;

import com.spring.project.spring_lab.domain.enums.TransactionType;

public record TransactionResponseDTO(
        UUID transactionId,
        String senderName,
        String receiverName,
        Double amount,
        TransactionType type,
        LocalDateTime date) {
}
