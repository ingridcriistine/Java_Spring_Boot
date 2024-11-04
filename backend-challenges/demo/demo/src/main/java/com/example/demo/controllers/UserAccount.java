package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserData;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/create")
public class UserAccount {

    @Autowired
    UserService service;
    
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserData data) {
        
        if(!service.checkEmail(data.email())) {
            return new ResponseEntity<>("Email inválido", HttpStatus.OK);
        }

        if(!service.checkUsername(data.username())) {
            return new ResponseEntity<>("Username inválido", HttpStatus.OK);
        }

        if(!service.checkPassword(data.password())) {
            return new ResponseEntity<>("Senha inválida", HttpStatus.OK);
        }

        if(service.addUser(data.username(), data.password(), data.email()) == null) {
            return new ResponseEntity<>("Dados inválidos", HttpStatus.OK);
        }

        return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.OK);

    }
        
}
