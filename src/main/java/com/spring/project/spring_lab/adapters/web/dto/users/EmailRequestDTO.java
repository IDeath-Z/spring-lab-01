package com.spring.project.spring_lab.adapters.web.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDTO(@NotBlank @Email String email) {
}
