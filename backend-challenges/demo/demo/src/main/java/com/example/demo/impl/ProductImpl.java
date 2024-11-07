package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.Token;
import com.example.demo.model.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;

public class ProductImpl implements ProductService{

    @Autowired
    ProductRepository repo;

    @Override
    public Product addProduct(Token token, String titulo, Float valor) {
        var products = repo.findByTitulo(titulo);

        if(!products.isEmpty()) {
            return null;
        }

        if(!checkEmail(token.getEmail())) {
            return null;
        }

        var product = new Product();
        product.setTitulo(titulo);
        product.setValor(valor);
        repo.saveAndFlush(product);

        return product;
    }

    @Override
    public Boolean checkEmail(String email) {
        
        if(!email.endsWith("@loja.com")) {
            return false;
        }

        return true;
    }
    
}
