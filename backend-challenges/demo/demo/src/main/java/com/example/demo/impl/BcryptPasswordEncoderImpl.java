package com.example.demo.impl;

import com.example.demo.services.PasswordEncoderService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderImpl implements PasswordEncoderService {

    @Override
    public String encode(String password)
    {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public Boolean validatePass(String password)
    {
        if(!encode(password).matches(password)) {
            return false;
        }
        return true;
    }

}
