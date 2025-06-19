package com.spring.project.spring_lab.domain.enums;

import lombok.Getter;

@Getter
public enum Role {

    USER("USER"),
    SHOPKEEPER("SHOPKEEPER");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}
