package com.spring.project.spring_lab.adapters.web.dto.transaction;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record TransferRequestDTO(
                @NotBlank Double amount,
                @NotBlank UUID payer,
                @NotBlank UUID payee) {
}
