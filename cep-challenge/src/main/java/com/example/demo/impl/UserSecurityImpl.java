package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.UserService;

import com.example.demo.dto.Token;

public class UserSecurityImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoderService service;

    @Override
    public User login(String login, String password) {
        var user = repo.findByUsername(login);

        if(user.isEmpty()) {
            return null;
        }

        if(!service.validatePass(password, user.get(0).getPassword())) {
            return null;
        }

        isAdmin(user.get(0).getUsercode());

        return user.get(0);
    }

    @Override
    public User addUser(String username, String password, Token usercode) {

        var userName = repo.findByUsername(username);

        if(!userName.isEmpty()) {
            return null;
        }

        if(!checkUsername(username) || !checkPassword(password)) {
            return null;
        }

        var user = new User();
        user.setUsername(username);
        user.setPassword(service.encode(password));
        user.setUsercode(usercode);
        user.setAdmin(isAdmin(usercode));
        repo.saveAndFlush(user);

        return user;
    }

    @Override
    public Boolean checkPassword(String password) {

        if(password.isEmpty() || password.length() < 8) {
            return false;
        }

        boolean passwordLower = false;
        boolean passwordUpper = false;
        boolean passwordDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                passwordLower = true;
            }

            if (Character.isUpperCase(c)) {
                passwordUpper = true;
            }

            if (Character.isDigit(c)) {
                passwordDigit = true;
            }
        }
        
        if(!passwordDigit || !passwordLower || !passwordUpper) {
            return false;
        }

        return true;

    }

    @Override
    public Boolean checkUsername(String username) {

        if(username.length() < 4 || username.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean changePass(String username, String senha, String newPassword, String repeatPassword) {
        var user = repo.findByUsername(username);

        if(user.isEmpty()) {
            return false;
        }

        if(user.get(0).getPassword().equals(senha)) {
            if(newPassword.equals(repeatPassword)) {
                if(checkPassword(repeatPassword)) {
                    user.get(0).setPassword(newPassword);
                    repo.saveAndFlush(user.get(0));
                    return true;
                }
            }
        }

        return false;
    }

    @Override 
    public Boolean isAdmin(Token usercode) {
        var user = usercode.getUsername();

        if(!user.equals("Admin")) {
            return false;
        }

        return true;
    }
    
    
}
