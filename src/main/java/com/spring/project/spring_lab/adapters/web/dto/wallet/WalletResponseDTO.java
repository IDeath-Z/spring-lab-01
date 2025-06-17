package com.spring.project.spring_lab.adapters.web.dto.wallet;

import java.util.UUID;

public record WalletResponseDTO(
        UUID id,
        Double balance) {
}
