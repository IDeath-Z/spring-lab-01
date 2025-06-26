package com.spring.project.spring_lab.adapters.web.dto.transaction;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DepositRequestDTO(
                @NotBlank @NotBlank Double amount,
                @NotBlank UUID wallet) {

}
