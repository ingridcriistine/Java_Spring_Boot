package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductData;
import com.example.demo.model.Product;

@RestController
@RequestMapping("")
public class ProductController {
    
    @Autowired
    Product service;

    @PostMapping("/product") 
    public ResponseEntity<String> createProduct(@RequestBody ProductData data) {

        if(service.addUser(data.token(), data.titulo(), data.valor()) == null){
            return new ResponseEntity<>("Dados inválidos", HttpStatus.OK);
        }

        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
    }
}
