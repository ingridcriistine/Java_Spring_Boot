package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Cep;
import com.example.demo.repositories.CepRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.StateRepository;
import com.example.demo.services.CepService;

public class CepImpl implements CepService {

    @Autowired
    CepRepository repo;

    @Autowired
    CityRepository repoCity;

    @Autowired
    StateRepository repoState;

    @Override
    public Cep addCep(String rua, String bairro, String cidade, String estado) {
        var city = repoCity.findByCity(cidade);
        var state = repoState.findByState(estado);
        var cityCode = city.get(0).getCode();
        var stateCode = state.get(0).getCode();

        var version = "01";
        var res = cityCode + stateCode * 1000;
        var verificationDigit = (stateCode + cityCode + res) % 100;

        var cepCode = version + "-" + String.valueOf(stateCode) + String.valueOf(cityCode) + String.valueOf(res) + String.valueOf(verificationDigit);

        var cep = new Cep();
        cep.setRua(rua);
        cep.setBairro(bairro);
        cep.setCidade(city.get(0));
        cep.setEstado(state.get(0));
        cep.setCode(cepCode);

        return cep;
    } 
    
}
