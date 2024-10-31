package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CitiesData;
import com.example.demo.repositories.CityRepository;

@RestController
@RequestMapping("/cities")
public class CitiesIndex {
    
    @Autowired
    CityRepository repo;

    @GetMapping("")
    public ResponseEntity<List<CitiesData>> getCities() {
        var cities = repo.findAll()
            .stream()
            .map(c -> new CitiesData(c.getName(), c.getState(), c.getCountry()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<CitiesData>> getByName(@PathVariable String name) {
        var cities = repo.findByName(name)
            .stream()
            .map(c -> new CitiesData(c.getName(), c.getState(), c.getCountry()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
