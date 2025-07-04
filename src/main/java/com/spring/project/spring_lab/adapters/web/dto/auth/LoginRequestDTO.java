package com.spring.project.spring_lab.adapters.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final record LoginRequestDTO(
                @NotBlank @Email String email,
                @NotBlank String password) {
}
