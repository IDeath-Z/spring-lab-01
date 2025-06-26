package com.spring.project.spring_lab.adapters.web.dto.wallet;

import java.util.List;

public record WalletPageResponseDTO(
        Integer totalPages,
        List<WalletResponseDTO> wallets) {

}
