package com.spring.project.spring_lab.domain.exceptions.account;

public class CpfAlreadyRegisteredException extends RuntimeException {

    public CpfAlreadyRegisteredException(String cpf) {

        super("An account is already registered with this cpf: " + cpf);
    }
}
