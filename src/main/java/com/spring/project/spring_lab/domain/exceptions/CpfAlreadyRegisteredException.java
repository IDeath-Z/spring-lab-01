package com.spring.project.spring_lab.domain.exceptions;

public class CpfAlreadyRegisteredException extends RuntimeException {

    public CpfAlreadyRegisteredException(String cpf) {

        super("A user is already registered with this cpf: " + cpf);
    }
}
