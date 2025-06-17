package com.spring.project.spring_lab.application.mappers;

import org.springframework.stereotype.Component;

import com.spring.project.spring_lab.adapters.web.dto.wallet.WalletResponseDTO;
import com.spring.project.spring_lab.domain.Wallet;

@Component
public class WalletMapper {

    public WalletResponseDTO toResponseDTO(Wallet wallet) {

        return new WalletResponseDTO(
                wallet.getId(),
                wallet.getBalance());
    }

}
