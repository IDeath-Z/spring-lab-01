package com.spring.project.spring_lab.adapters.web.exceptions;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.project.spring_lab.domain.exceptions.CnpjAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.CpfAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.AccountAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.AccountNotFoundException;

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
    public ResponseEntity<?> UserNotFound(AccountNotFoundException ex) {

        var body = Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Not Found",
                "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}