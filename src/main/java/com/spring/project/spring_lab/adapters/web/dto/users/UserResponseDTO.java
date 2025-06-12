package com.spring.project.spring_lab.adapters.web.dto.users;

import java.util.UUID;

import com.spring.project.spring_lab.domain.enums.Roles;

public record UserResponseDTO(
                UUID id,
                String fullName,
                String email,
                Roles role) {
}
