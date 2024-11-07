package com.trevis.passwordapp.services;

public interface JWTService<T> {
    String get(T token);
    T validate(String jwt);
}