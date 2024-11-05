package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChangePass;
import com.example.demo.dto.UserData;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("")
public class UserAccount {

    @Autowired
    UserService service;
    
    @PostMapping("/create")
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

    @PatchMapping("/changepassword")
    public ResponseEntity<String> changePass(@RequestBody ChangePass data) {

        if(!service.changePass(data.username(), data.password(), data.newPassword(), data.repeatPassword())) {
            return new ResponseEntity<>("Não foi possível alterar a senha!", HttpStatus.OK);
        }


        return new ResponseEntity<>("Senha alterada com sucesso!", HttpStatus.OK);
    }
}
