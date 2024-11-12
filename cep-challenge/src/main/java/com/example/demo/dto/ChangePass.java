package com.example.demo.dto;

public record ChangePass(
    String username,
    String password,
    String newPassword,
    String repeatPassword
) {} 
