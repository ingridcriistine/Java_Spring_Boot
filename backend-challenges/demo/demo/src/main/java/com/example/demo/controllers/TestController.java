package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SumResult;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping
    @RequestMapping("/{a}")
    public SumResult test(@PathVariable Integer a, Integer b) {
        if (b == null) 
            b = 2;

        var result = a + b;
        var isEven = result % 2 == 0;

        return new SumResult(result, isEven);
    }
}