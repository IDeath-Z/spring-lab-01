package com.spring.project.spring_lab.adapters.web.dto.account;

import java.util.UUID;

import com.spring.project.spring_lab.adapters.web.dto.wallet.WalletResponseDTO;
import com.spring.project.spring_lab.domain.enums.Role;

public record AccountResponseDTO(
                UUID id,
                String fullName,
                String email,
                Role role,
                boolean isActive,
                WalletResponseDTO mainWallet) {
}
