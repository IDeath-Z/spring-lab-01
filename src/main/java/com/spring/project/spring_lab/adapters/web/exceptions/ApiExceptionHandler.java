package com.spring.project.spring_lab.adapters.web.exceptions;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.project.spring_lab.domain.exceptions.account.AccountAlreadyDeactivatedException;
import com.spring.project.spring_lab.domain.exceptions.account.AccountAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.account.AccountNotFoundException;
import com.spring.project.spring_lab.domain.exceptions.account.AccountTokenMismatchException;
import com.spring.project.spring_lab.domain.exceptions.account.CnpjAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.account.CpfAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.transaction.TransactionNotAuthorizedException;
import com.spring.project.spring_lab.domain.exceptions.wallet.BalanceMustBeZeroException;
import com.spring.project.spring_lab.domain.exceptions.wallet.CanNotDeactivateMainWalletException;
import com.spring.project.spring_lab.domain.exceptions.wallet.InsufficientFundsException;
import com.spring.project.spring_lab.domain.exceptions.wallet.WalletAlreadyDeactivatedException;
import com.spring.project.spring_lab.domain.exceptions.wallet.WalletNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AccountAlreadyRegisteredException.class)
    public ResponseEntity<?> userAlreadyRegistered(AccountAlreadyRegisteredException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "error", "Conflict",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(CpfAlreadyRegisteredException.class)
    public ResponseEntity<?> cpfAlreadyRegistered(CpfAlreadyRegisteredException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "error", "Conflict",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(CnpjAlreadyRegisteredException.class)
    public ResponseEntity<?> cnpjAlreadyRegistered(CnpjAlreadyRegisteredException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "error", "Conflict",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> accountNotFound(AccountNotFoundException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "NotFound",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<?> walletNotFound(WalletNotFoundException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "NotFound",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<?> insufficientFunds(InsufficientFundsException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.FORBIDDEN.value(),
                "error", "InsufficientFounds",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(TransactionNotAuthorizedException.class)
    public ResponseEntity<?> transactionNotAuthorized(TransactionNotAuthorizedException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.FORBIDDEN.value(),
                "error", "TransactionNotAuthorized",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(AccountAlreadyDeactivatedException.class)
    public ResponseEntity<?> accountAlreadyDeactivated(AccountAlreadyDeactivatedException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "error", "Conflict",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(BalanceMustBeZeroException.class)
    public ResponseEntity<?> balanceMustBeZero(BalanceMustBeZeroException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "BadRequest",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(AccountTokenMismatchException.class)
    public ResponseEntity<?> accountTokenMismatchException(AccountTokenMismatchException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.FORBIDDEN.value(),
                "error", "Forbidden",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(WalletAlreadyDeactivatedException.class)
    public ResponseEntity<?> walletAlreadyDeactivatedException(WalletAlreadyDeactivatedException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.CONFLICT.value(),
                "error", "Conflict",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(CanNotDeactivateMainWalletException.class)
    public ResponseEntity<?> canNotDeactivateMainWalletException(CanNotDeactivateMainWalletException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "BadRequest",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}