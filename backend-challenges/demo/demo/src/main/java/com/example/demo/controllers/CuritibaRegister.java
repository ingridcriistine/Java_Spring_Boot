package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CuritibaData;

@CrossOrigin(origins = {"http://localhost:5257/"})
@RestController
@RequestMapping("/curitiba")
public class CuritibaRegister {
    
    @GetMapping
    @RequestMapping()
    public ResponseEntity<CuritibaData> register(Integer cep, String cpf) {

        // String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        // RestTemplate restTemplate = new RestTemplate();
        // String result = restTemplate.getForObject(uri, String.class);

        // System.out.println(result);
        

        // String jsonCep = @RequestMapping("viacep.com.br/ws/" + cep + "/json/");
        // Boolean pertence = true;
        // if(!pertence) {
        //     return ResponseEntity.notFound().build();
        // }

        Integer digito = 0;
        Integer somaDigito = 0;
        Integer primeiroDigito = 0;
        Integer segundoDigito = 0;

        for (int i = 1; i < 10; i++) {
            digito = Integer.parseInt(String.valueOf(cpf.charAt(i-1))) * i;
            somaDigito += digito;
            // System.out.println(digito); 
        }

        primeiroDigito = somaDigito % 11;
        if(primeiroDigito == 10) {
            primeiroDigito = 0;
        }
        // System.out.println(primeiroDigito);

        digito = 0;
        somaDigito = 0;
        for (int i = 0; i < 9; i++) {
            digito = Integer.parseInt(String.valueOf(cpf.charAt(i))) * i;
            somaDigito += digito;
            // System.out.println(digito); 
        }
        somaDigito += (primeiroDigito * 9);

        segundoDigito = somaDigito % 11;
        if(segundoDigito == 10) {
            segundoDigito = 0;
        }
        // System.out.println(segundoDigito);

        for (int i = 1; i < 10; i++) {
            digito = Integer.parseInt(String.valueOf(cpf.charAt(i-1))) * i;
            // System.out.println(digito); 
        }
        
        if(Integer.parseInt(String.valueOf(cpf.charAt(9))) != primeiroDigito || Integer.parseInt(String.valueOf(cpf.charAt(10))) != segundoDigito) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new CuritibaData(true, "Dados válidos!"));
    }
}
