package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReverseData;
import com.example.demo.services.ReverseService;

@CrossOrigin(origins = {"http://localhost:5257/"})
@RestController
@RequestMapping("/reverse")
public class Reverse {

    @Autowired
    ReverseService service;
    
    @GetMapping
    @RequestMapping("/{word}")
    public ResponseEntity<ReverseData> reverse(@PathVariable String word) {
        return ResponseEntity.ok(service.reverse(word));
    }
}