package com.spring.project.spring_lab.adapters.web.dto.account;

import com.spring.project.spring_lab.domain.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRequestDTO(
        @NotBlank @Size(max = 100) String fullName,
        @NotBlank boolean isJuridicPerson,
        @NotBlank @Size(min = 11, max = 14) String taxId,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6, max = 20) String password,
        @NotBlank Role role) {
}
