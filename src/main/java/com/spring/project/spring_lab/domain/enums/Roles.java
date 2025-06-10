package com.spring.project.spring_lab.domain.enums;

import lombok.Getter;

@Getter
public enum Roles {

    USER("USER"),
    SHOPKEEPER("SHOPKEEPER");

    private final String role;

    Roles(String role) {
        this.role = role;
    }
}
