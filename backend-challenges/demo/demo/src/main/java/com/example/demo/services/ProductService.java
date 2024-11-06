package com.example.demo.services;

import com.example.demo.dto.Token;
import com.example.demo.model.Product;

public interface ProductService {
    Product addProduct(Token token, String titulo, Float valor);
    Boolean checkEmail(String email);
}
