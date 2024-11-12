package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StateData;
import com.example.demo.dto.CountStates;
import com.example.demo.dto.Token;
import com.example.demo.services.StateService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/state")
public class StateController {
    
    @Autowired
    UserService service;

    @Autowired
    StateService serviceState;


    @PostMapping("") 
    public ResponseEntity<String> createCity(@RequestAttribute("token") Token token, @RequestBody StateData data) {

        if(token == null) {
            return ResponseEntity.status(401).build();
        }

        if(!service.isAdmin(token)) {
            return ResponseEntity.status(403).build();
        }

        serviceState.addState(data.state(), data.code());

        return new ResponseEntity<>("Estado cadastrado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/count") 
    public ResponseEntity<CountStates> countCities() {

        Long qtd = serviceState.countStates();
        Long nextCode = serviceState.getNextCode();

        return new ResponseEntity<>(new CountStates(qtd, nextCode), HttpStatus.OK);
    }

    // @GetMapping("/{page&count}")
    // public ResponseEntity<List<StateData>> getStates(@PathVariable long page, @PathVariable long count) {

        

    //     return new ResponseEntity<>(states, HttpStatus.OK);
    // }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateState(@PathVariable long id, @RequestBody StateData data) {

        if(!serviceState.updateState(id, data.state(), data.code())) {
            return new ResponseEntity<>("Não foi possível alterar os dados de estado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Dados de estado alterados com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAllState(@PathVariable long id, @RequestBody StateData data) {

        if(!serviceState.updateAllState(id, data.state(), data.code())) {
            return new ResponseEntity<>("Não foi possível alterar os dados de estado!", HttpStatus.OK);
        }


        return new ResponseEntity<>("Dados de estado alterados com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteState(@PathVariable long id) {

        if(!serviceState.deleteState(id)) {
            return new ResponseEntity<>("Não foi possível deletar o estado!", HttpStatus.OK);
        }


        return new ResponseEntity<>("Estado deletado com sucesso!", HttpStatus.OK);
    }


}
