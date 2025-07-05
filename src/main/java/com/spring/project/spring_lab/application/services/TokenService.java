package com.spring.project.spring_lab.application.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.spring.project.spring_lab.domain.Account;

@Service
public class TokenService {

    @Value("${SECURITY_TOKEN_SECRET}")
    private String secret;

    public String generateToken(Account account) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("lab01")
                    .withSubject(account.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {

            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("lab01")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {

            return "";
        }
    }

    public boolean isTokenValid(Account account) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!account.getEmail().equals(email)) {

            return false;
        } else {

            return true;
        }
    }

    private Instant genExpirationDate() {

        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
