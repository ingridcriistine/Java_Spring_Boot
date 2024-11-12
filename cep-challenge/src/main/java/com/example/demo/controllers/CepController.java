package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CepData;
import com.example.demo.dto.Token;
import com.example.demo.services.CepService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/cep")
public class CepController {
    
    @Autowired
    UserService service;

    @Autowired
    CepService serviceCep;


    @PostMapping("") 
    public ResponseEntity<String> createCep(@RequestAttribute("token") Token token, @RequestBody CepData data) {

        if(token == null) {
            return ResponseEntity.status(401).build();
        }

        if(!service.isAdmin(token)) {
            return ResponseEntity.status(403).build();
        }

        var cep = serviceCep.addCep(data.rua(), data.bairro(), data.cidade(), data.estado());

        // return new ResponseEntity<CepData>(cep.getRua(), cep.getBairro(), cep.getCidade(), cep.getEstado(), cep.getCode());
        return new ResponseEntity<>("Cep cadastrado com sucesso!", HttpStatus.OK);
    }

    // @GetMapping("/{cep}") 
    // public ResponseEntity<CepData> getCep() {


    //     return new ResponseEntity<>(new CepData(cep.getRua(), cep.getBairro(), cep.getCidade(), cep.getEstado(), cep.getCode()), HttpStatus.OK);
    // }


}
