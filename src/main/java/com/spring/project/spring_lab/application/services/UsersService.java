package com.spring.project.spring_lab.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.spring_lab.adapters.web.dto.users.UserRequestDTO;
import com.spring.project.spring_lab.adapters.web.dto.users.UserResponseDTO;
import com.spring.project.spring_lab.application.mappers.UserMapper;
import com.spring.project.spring_lab.domain.Users;
import com.spring.project.spring_lab.domain.exceptions.CnpjAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.CpfAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.UserAlreadyRegisteredException;
import com.spring.project.spring_lab.domain.exceptions.UserNotFoundException;
import com.spring.project.spring_lab.infrastructure.persistence.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO addUser(UserRequestDTO request) {

        if (usersRepository.existsByEmail(request.email())) {

            throw new UserAlreadyRegisteredException(request.email());
        }

        if (usersRepository.existsByTaxId(request.taxId())) {
            if (request.isJuridicPerson()) {

                throw new CnpjAlreadyRegisteredException(request.taxId());
            } else {

                throw new CpfAlreadyRegisteredException(request.taxId());
            }
        }

        Users user = userMapper.toDomain(request);
        return userMapper.toResponeDTO(usersRepository.save(user));
    }

    public UserResponseDTO fetchUser(UUID id) {

        Users user = usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toResponeDTO(user);
    }

    public UserResponseDTO fetchUser(String email) {

        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        return userMapper.toResponeDTO(user);
    }

}
