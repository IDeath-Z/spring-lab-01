package com.spring.project.spring_lab.adapters.web.dto.transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponseDTO(
                UUID transactionId,
                String senderName,
                String receiverName,
                Double amount,
                LocalDateTime date) {
}
