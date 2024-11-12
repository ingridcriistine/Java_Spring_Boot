package com.example.demo.services;
import com.example.demo.model.User;

import com.example.demo.dto.Token;

public interface UserService {
    User login(String username, String password);
    User addUser(String username, String password, Token usercode);
    Boolean checkPassword(String password);
    Boolean checkUsername(String username);
    Boolean changePass(String username, String senha, String newPassword, String repeatPassword);
    Boolean isAdmin(Token usercode);
}
