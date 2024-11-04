package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

public class UserImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public User addUser(String username, String password, String email) {
        var userName = repo.findByUsername(username);
        var userEmail = repo.findByEmail(email);

        if(!userName.isEmpty() || !userEmail.isEmpty()) {
            return null;
        }

        if(!checkUsername(username) || !checkEmail(email) || !checkPassword(password)) {
            return null;
        }

        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        repo.saveAndFlush(user);

        return user;
    }

    @Override
    public Boolean checkEmail(String email) {

        if(email.length() < 4 || email.isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        
        if (atIndex < 1 || dotIndex < atIndex + 1 || dotIndex >= email.length() - 1) {
            return false;
        }

        return true;
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
        
}
