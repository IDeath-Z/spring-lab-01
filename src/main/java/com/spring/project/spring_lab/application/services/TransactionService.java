package com.spring.project.spring_lab.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.spring.project.spring_lab.adapters.web.dto.transaction.DepositRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.transaction.TransactionAuthDTO;
import com.spring.project.spring_lab.adapters.web.dto.transaction.TransactionResponseDTO;
import com.spring.project.spring_lab.application.mappers.TransactionMapper;
import com.spring.project.spring_lab.domain.Transaction;
import com.spring.project.spring_lab.domain.Wallet;
import com.spring.project.spring_lab.domain.enums.TransactionType;
import com.spring.project.spring_lab.domain.exceptions.transaction.TransactionNotAuthorizedException;
import com.spring.project.spring_lab.domain.exceptions.wallet.WalletNotFoundException;
import com.spring.project.spring_lab.infrastructure.persistence.TransactionRepository;
import com.spring.project.spring_lab.infrastructure.persistence.WalletRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private WalletRepository walletRepository;

    private boolean isAuth() {

        try {

            String apiUrl = "https://util.devi.tools/api/v2/authorize";
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<TransactionAuthDTO> response = restTemplate.getForEntity(
                    apiUrl,
                    TransactionAuthDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {

                TransactionAuthDTO body = response.getBody();
                return body != null &&
                        "success".equals(body.status()) &&
                        body.data().authorization();
            }

            return false;
        } catch (RestClientException e) {

            return false;
        }
    }

    public TransactionResponseDTO deposit(DepositRequestDTO request) {

        if (!isAuth()) {

            throw new TransactionNotAuthorizedException();
        }

        Wallet wallet = walletRepository.findById(request.wallet())
                .orElseThrow(() -> new WalletNotFoundException(request.wallet()));

        Transaction transaction = new Transaction();
        transaction.setSenderWallet(null);
        transaction.setReceiverWallet(wallet);
        transaction.setAmount(request.amount());
        transaction.setType(TransactionType.DEPOSIT);

        wallet.setBalance(wallet.getBalance() + request.amount());
        wallet.getReceivedTransactions().add(transaction);

        return transactionMapper.toResponseDTO(transactionRepository.save(transaction));
    }
}
