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
import com.example.demo.dto.UsercodeData;
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
    public ResponseEntity<UsercodeData> createUser(@RequestBody UserData data) {

        var user = service.login(data.username(), data.password());

        if(user == null) {
            return new ResponseEntity<>(new UsercodeData("", "Dados inválidos"), HttpStatus.OK);
        }

        var usercode = new Token();
        usercode.setId(user.getId());
        usercode.setUsername(user.getUsername());
        
        var generateToken = serviceJwt.get(usercode);

        service.addUser(data.username(), data.password(), usercode);

        return new ResponseEntity<>(new UsercodeData(generateToken, "Usuário cadastrado com sucesso!"), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenData> login(@RequestBody LoginData data) {
        var user = service.login(data.login(), data.password());

        if(user == null) {
            return new ResponseEntity<>(new TokenData("Dados inválidos", ""), HttpStatus.OK);
        }

        var token = new Token();
        token.setId(user.getId());
        token.setUsername(user.getUsername());
        
        var generateToken = serviceJwt.get(token);

        return new ResponseEntity<>(new TokenData("Token gerado com sucesso!", generateToken), HttpStatus.OK);
    }

    // @PostMapping("/admin")
    // public ResponseEntity<TokenData> toAdmin(@RequestBody Token data) {

    //     var user = service.findByUsercode(data.user);


    //     return new ResponseEntity<>(new TokenData("Token gerado com sucesso!", generateToken), HttpStatus.OK);
    // }
}
