package com.trevis.passwordapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.trevis.passwordapp.dto.Token;
import com.trevis.passwordapp.services.JWTService;
import com.trevis.passwordapp.services.impl.DefaultJWTService;


@Configuration
public class DependenciesConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public JWTService<Token> jwtService() {
        return new DefaultJWTService();
    }
}