package com.example.demo.services;

import com.example.demo.model.City;

public interface CityService {
    City addCity(String city, long code);
    Long countCities();
    Long getNextCode();
}
