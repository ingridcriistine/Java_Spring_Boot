package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.dto.Token;
import com.example.demo.dto.TokenData;
import com.example.demo.dto.UserData;
import com.example.demo.services.JwtTokenService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("")
public class UserSecurity {

    @Autowired
    UserService service;

    @Autowired
    JwtTokenService<Token> serviceJwt;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserData data) {

        if(service.addUser(data.username(), data.password(), data.email()) == null) {
            return new ResponseEntity<>("Dados inválidos", HttpStatus.OK);
        }

        return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenData> login(@RequestBody LoginData data) {
        var user = service.login(data.login(), data.password());

        if(user == null) {
            return new ResponseEntity<>(new TokenData("Dados inválidos", ""), HttpStatus.OK);
        }

        var token = new Token();
        token.setId(user.getId());
        token.setEmail(user.getEmail());
        token.setUsername(user.getUsername());
        
        var generateToken = serviceJwt.get(token);

        return new ResponseEntity<>(new TokenData("Token gerado com sucesso!", generateToken), HttpStatus.OK);
    }
}
