package com.trevis.passwordapp;

public record ApiResponse<T>(T data, String message) { }