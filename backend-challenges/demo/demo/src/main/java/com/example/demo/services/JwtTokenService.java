package com.example.demo.services;

public interface JwtTokenService<T> {
    String get(T token);
    T validate(String jwt);
}
