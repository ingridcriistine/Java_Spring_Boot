package com.example.demo.services;

import com.example.demo.model.User;

public interface UserService {
    User login(String username, String password);
    User addUser(String username, String password, String email);
    Boolean checkEmail(String email);
    Boolean checkPassword(String password);
    Boolean checkUsername(String username);
    Boolean changePass(String username, String senha, String newPassword, String repeatPassword);
}
