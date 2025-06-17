package com.spring.project.spring_lab.adapters.web.dto.account;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record AccountUuidRequestDTO(@NotBlank UUID accountId) {

}
