package com.trevis.passwordapp.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.passwordapp.dto.Token;
import com.trevis.passwordapp.model.PasswordRequest;
import com.trevis.passwordapp.model.Role;
import com.trevis.passwordapp.repositories.PasswordRequestRepository;
import com.trevis.passwordapp.repositories.RoleRepository;
import com.trevis.passwordapp.repositories.UserRepository;

@RestController
@RequestMapping("/password")
public class PasswordController {
    
    @Autowired
    PasswordRequestRepository reqRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping
    public ResponseEntity<String> generate(@RequestAttribute("token") Token token)
    {
        var role = new Role();
        role.setName(token.getRole());
        var roles = roleRepo.findAll(Example.of(role));
        if (roles.isEmpty())
            return null;
        var limit = roles.get(0).getGenerationLimit();

        var optuser = userRepo.findById(token.getId());
        if (!optuser.isPresent())
            return null;
        var user = optuser.get();
        var today = new Date(System.currentTimeMillis());
        
        Long requests = user.getRequests()
            .stream()
            .filter(r -> isSameDay(r.getGenerationDate(), today))
            .count();
        
        if (requests >= limit) {
            return new ResponseEntity<String>(
                "Você não pode pedir mais senhas hoje",
                HttpStatus.FORBIDDEN
            );
        }

        PasswordRequest request = new PasswordRequest();
        request.setUser(user);
        request.setGenerationDate(today);
        reqRepo.save(request);

        return ResponseEntity.ok("Uma senha bem dificil");
    }

    boolean isSameDay(Date date, Date today) {
        return date.toString().equals(today.toString());
    }

}