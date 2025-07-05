package com.spring.project.spring_lab.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.spring.project.spring_lab.adapters.web.dto.transaction.DepositRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.transaction.TransactionAuthDTO;
import com.spring.project.spring_lab.adapters.web.dto.transaction.TransactionResponseDTO;
import com.spring.project.spring_lab.adapters.web.dto.transaction.TransferRequestDTO;
import com.spring.project.spring_lab.application.mappers.TransactionMapper;
import com.spring.project.spring_lab.domain.Account;
import com.spring.project.spring_lab.domain.Transaction;
import com.spring.project.spring_lab.domain.Wallet;
import com.spring.project.spring_lab.domain.enums.TransactionType;
import com.spring.project.spring_lab.domain.exceptions.account.AccountNotFoundException;
import com.spring.project.spring_lab.domain.exceptions.transaction.TransactionNotAuthorizedException;
import com.spring.project.spring_lab.domain.exceptions.transaction.AccountTokenMismatchException;
import com.spring.project.spring_lab.domain.exceptions.wallet.InsufficientFundsException;
import com.spring.project.spring_lab.domain.exceptions.wallet.WalletNotFoundException;
import com.spring.project.spring_lab.infrastructure.persistence.AccountRepository;
import com.spring.project.spring_lab.infrastructure.persistence.TransactionRepository;
import com.spring.project.spring_lab.infrastructure.persistence.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenService tokenService;

    private boolean isAuth() {

        try {

            String apiUrl = "https://util.devi.tools/api/v2/authorize";
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<TransactionAuthDTO> response = restTemplate.getForEntity(apiUrl, TransactionAuthDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {

                TransactionAuthDTO body = response.getBody();
                return body != null && "success".equals(body.status())
                        && body.data().authorization();
            }

            return false;
        } catch (RestClientException e) {

            return false;
        }
    }

    @Transactional
    public TransactionResponseDTO deposit(DepositRequestDTO request) {

        if (!isAuth()) {

            throw new TransactionNotAuthorizedException();
        }

        Wallet wallet = walletRepository.findById(request.wallet())
                .orElseThrow(() -> new WalletNotFoundException(request.wallet()));

        if (tokenService.isTokenValid(wallet.getAccount())) {

            throw new AccountTokenMismatchException();
        }

        Transaction transaction = new Transaction();
        transaction.setSenderWallet(null);
        transaction.setReceiverWallet(wallet);
        transaction.setAmount(request.amount());
        transaction.setType(TransactionType.DEPOSIT);

        wallet.setBalance(wallet.getBalance() + request.amount());
        wallet.getReceivedTransactions().add(transaction);

        return transactionMapper.toResponseDTO(transactionRepository.saveAndFlush(transaction));
    }

    @Transactional
    public TransactionResponseDTO transfer(TransferRequestDTO request) {

        if (!isAuth()) {

            throw new TransactionNotAuthorizedException();
        }

        Account payerAccount = accountRepository.findById(request.payer())
                .orElseThrow(() -> new AccountNotFoundException(request.payer()));

        if (tokenService.isTokenValid(payerAccount)) {

            throw new AccountTokenMismatchException();
        }

        Wallet senderWallet = payerAccount.getMainWallet();

        if (senderWallet.getBalance() < request.amount()) {

            throw new InsufficientFundsException();
        }

        Account payeeAccount = accountRepository.findById(request.payee())
                .orElseThrow(() -> new AccountNotFoundException(request.payee()));

        Wallet receiverWallet = payeeAccount.getMainWallet();

        senderWallet.setBalance(senderWallet.getBalance() - request.amount());
        receiverWallet.setBalance(receiverWallet.getBalance() + request.amount());

        Transaction transaction = new Transaction();
        transaction.setSenderWallet(senderWallet);
        transaction.setReceiverWallet(receiverWallet);
        transaction.setAmount(request.amount());
        transaction.setType(TransactionType.TRANSFER);

        senderWallet.getSendedTransactions().add(transaction);
        receiverWallet.getReceivedTransactions().add(transaction);

        return transactionMapper.toResponseDTO(transactionRepository.saveAndFlush(transaction));
    }
}
