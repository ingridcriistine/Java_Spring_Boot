package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductData;
import com.example.demo.dto.Token;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("")
public class ProductController {
    
    @Autowired
    ProductService service;

    @PostMapping("/product") 
    public ResponseEntity<String> createProduct(@RequestAttribute("token") Token token, @RequestBody ProductData data) {

        if(token == null) {
            return ResponseEntity.status(401).build();
        }

        if(!service.checkEmail(token.getEmail())) {
            return ResponseEntity.status(403).build();
        }

        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
    }
}
