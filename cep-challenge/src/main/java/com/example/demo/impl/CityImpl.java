package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.City;
import com.example.demo.repositories.CityRepository;
import com.example.demo.services.CityService;

public class CityImpl implements CityService {

    @Autowired
    CityRepository repo;

    @Override
    public City addCity(String city, long code) {
        var citiesName = repo.findByCity(city);
        var citiesCode = repo.findByCode(code);

        if(!citiesName.isEmpty() || !citiesCode.isEmpty()) {
            return null;
        }

        var newCity = new City();
        newCity.setCity(city);
        newCity.setCode(code);
        newCity.setNextCode(code+1);
        repo.saveAndFlush(newCity);

        return newCity;
    }

    @Override
    public Long countCities() {
        return repo.count();
    }

    @Override
    public Long getNextCode() {
        var lastCity = countCities();
        var city = repo.findById(lastCity);
        var nextCode = city.get().getNextCode();

        return nextCode;
    }
    
}
