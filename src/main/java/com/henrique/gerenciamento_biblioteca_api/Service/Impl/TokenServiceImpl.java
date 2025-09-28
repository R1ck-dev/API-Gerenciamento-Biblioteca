package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;
import com.henrique.gerenciamento_biblioteca_api.Service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateToken(UserModel userModel) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("biblioteca-api")
                    .withSubject(userModel.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
                return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("biblioteca-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
    
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
