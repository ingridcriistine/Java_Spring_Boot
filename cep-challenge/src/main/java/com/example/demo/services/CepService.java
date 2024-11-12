package com.example.demo.services;

import com.example.demo.model.Cep;

public interface CepService {
    Cep addCep(String rua, String bairro, String cidade, String estado);
}
