package com.spring.project.spring_lab.domain.exceptions;

public class CnpjAlreadyRegisteredException extends RuntimeException {

    public CnpjAlreadyRegisteredException(String cnpj) {

        super("An account is already registered with this cnpj: " + cnpj);
    }
}
