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

import com.example.demo.dto.CityData;
import com.example.demo.dto.CountCities;
import com.example.demo.dto.Token;
import com.example.demo.services.CityService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("")
public class CityController {
    
    @Autowired
    UserService service;

    @Autowired
    CityService serviceCity;


    @PostMapping("/city") 
    public ResponseEntity<String> createCity(@RequestAttribute("token") Token token, @RequestBody CityData data) {

        if(token == null) {
            return ResponseEntity.status(401).build();
        }

        if(!service.isAdmin(token)) {
            return ResponseEntity.status(403).build();
        }

        serviceCity.addCity(data.city(), data.code());

        return new ResponseEntity<>("Cidade cadastrada com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/city/count") 
    public ResponseEntity<CountCities> countCities() {

        Long qtd = serviceCity.countCities();
        Long nextCode = serviceCity.getNextCode();

        return new ResponseEntity<>(new CountCities(qtd, nextCode), HttpStatus.OK);
    }
}
