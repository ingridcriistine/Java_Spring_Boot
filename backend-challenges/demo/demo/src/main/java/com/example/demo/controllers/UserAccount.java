package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserData;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/create")
public class UserAccount {
    
    @Autowired
    UserRepository repo;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserData data) {
        var userName = repo.findByUsername(data.username());
        var userEmail = repo.findByEmail(data.email());

        if(!userName.isEmpty() || !userEmail.isEmpty()) {
            return new ResponseEntity<>("Usuário/Email já está em uso", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.OK);
    }
}
