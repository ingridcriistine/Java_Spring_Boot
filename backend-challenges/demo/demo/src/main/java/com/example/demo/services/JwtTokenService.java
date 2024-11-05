package com.example.demo.services;

import java.time.Instant;

import com.example.demo.model.User;

public interface JwtTokenService {
    String generateToken(User user);
    String getSubjectFromToken(String token);
    Instant creationDate();
    Instant expirationDate();
}
