package com.spring.project.spring_lab.application.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.project.spring_lab.adapters.web.dto.users.UserRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.users.UserResponseDTO;
import com.spring.project.spring_lab.domain.Users;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Users toDomain(UserRequestDTO request) {

        Users user = new Users();
        user.setFullname(request.fullName());
        user.setTaxId(request.taxId());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        return user;
    }

    public UserResponseDTO toResponeDTO(Users user) {

        return new UserResponseDTO(
                user.getId(),
                user.getFullname(),
                user.getEmail(),
                user.getRole());
    }
}
