package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VehicleDto;
import com.example.demo.model.Vehicle;
import com.example.demo.repositories.VehicleRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    
    @Autowired
    VehicleRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        var vehicle = repo.findById(id);

        // outra forma
        // Vehicle example = new Vehicle();
        // example.setType("Caminhão");
        // repo.findOne(Example.of(example));
        
        if(!vehicle.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
    }

    @GetMapping("/find/{type}")
    public ResponseEntity<List<VehicleDto>> getByType(@PathVariable String type) {
        var vehicles = repo.findByType(type)
            .stream()
            .map(v -> new VehicleDto(v.getWhellCount(), v.getType()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

}
