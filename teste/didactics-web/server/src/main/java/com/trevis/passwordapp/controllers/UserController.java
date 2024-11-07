package com.trevis.passwordapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.passwordapp.dto.LoginData;
import com.trevis.passwordapp.dto.Token;
import com.trevis.passwordapp.dto.UserData;
import com.trevis.passwordapp.model.User;
import com.trevis.passwordapp.repositories.RoleRepository;
import com.trevis.passwordapp.repositories.UserRepository;
import com.trevis.passwordapp.services.JWTService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository rolesRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService<Token> jwtService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserData userData) {

        if (userData.email() == null || userData.password() == null || userData.username() == null) {
            return new ResponseEntity<>(
                "username, email and password are expected.", 
                HttpStatus.BAD_REQUEST
            );
        }

        if (!repo.findByEmail(userData.email()).isEmpty()) {
            return new ResponseEntity<>(
                "email already is in use.", 
                HttpStatus.BAD_REQUEST
            );
        }

        if (!repo.findByUsername(userData.username()).isEmpty()) {
            return new ResponseEntity<>(
                "username already is in use.", 
                HttpStatus.BAD_REQUEST
            );
        }

        var freeRole = rolesRepo.findAll()
            .stream()
            .filter(r -> r.getName().equals("Free"))
            .findAny();
        
        if (!freeRole.isPresent()) {
            return new ResponseEntity<>(
                "free users cannot be created.", 
                HttpStatus.BAD_REQUEST
            );
        }
        
        var securePass = encoder.encode(userData.password());

        User user = new User();
        user.setEmail(userData.email());
        user.setUsername(userData.username());
        user.setPassword(securePass);
        user.setRole(freeRole.get());
        repo.save(user);

        return new ResponseEntity<>(
            "user create successfully",
            HttpStatus.CREATED
        );

    }

    @PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody LoginData loginData) {

        if (loginData.login() == null || loginData.password() == null) {
            return new ResponseEntity<>(
                "login and password are expected.", 
                HttpStatus.BAD_REQUEST
            );
        }
        var users = repo.login(loginData.login());

        if (users.isEmpty()) {
            return new ResponseEntity<>(
                "The user do not exists.", 
                HttpStatus.UNAUTHORIZED
            );
        }
        var user = users.get(0);

        if (!encoder.matches(loginData.password(), user.getPassword())) {
            return new ResponseEntity<>(
                "The password is incorrect.", 
                HttpStatus.UNAUTHORIZED
            );
        }

        Token token = new Token();
        token.setId(user.getId());
        token.setRole(user.getRole().getName());
        
        var jwt = jwtService.get(token);

        return new ResponseEntity<>(
            jwt,
            HttpStatus.OK
        );
    }

}