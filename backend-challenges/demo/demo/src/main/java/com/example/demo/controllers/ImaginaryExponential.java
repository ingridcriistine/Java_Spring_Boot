package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ImaginaryResult;
import com.example.demo.services.ImaExpService;

@CrossOrigin(origins = {"http://localhost:5257/"})
@RestController
@RequestMapping("/imaexp")
public class ImaginaryExponential {

    @Autowired
    ImaExpService service;
    
    @GetMapping
    @RequestMapping()
    public ResponseEntity<ImaginaryResult> imaExp(Double A, Double b) {

        return ResponseEntity.ok(service.imaExp(A, b));
    }
}
