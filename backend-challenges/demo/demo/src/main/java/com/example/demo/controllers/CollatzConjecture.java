package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CollatzData;

@CrossOrigin(origins = {"http://localhost:5257/"})
@RestController
@RequestMapping("/collatz")
public class CollatzConjecture {
    
    @GetMapping
    @RequestMapping()
    public ResponseEntity<CollatzData> collatz(Integer current, Integer step) {

        Integer new_current = 0;

        if(current < 0 || step < 0) {
            return ResponseEntity.badRequest().build();
        }

        for (int i = 0; i < step; i++) {
            if(current % 2 == 0) {
                new_current = current / 2;
            }
            else {
                new_current = 3 * current + 1;
            }
        }

        return ResponseEntity.ok(new CollatzData(new_current));
    }
}
