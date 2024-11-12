package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.dto.Token;
import com.example.demo.impl.BcryptPasswordEncoderImpl;
import com.example.demo.impl.CepImpl;
import com.example.demo.impl.CityImpl;
import com.example.demo.impl.JwtTokenImpl;
import com.example.demo.impl.StateImpl;
import com.example.demo.impl.UserSecurityImpl;
import com.example.demo.services.CepService;
import com.example.demo.services.CityService;
import com.example.demo.services.JwtTokenService;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.StateService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {

    @Bean
    @Scope("singleton")
    public UserService userService() {
        return new UserSecurityImpl();
    }

    @Bean
    @Scope("singleton")
    public PasswordEncoderService passwordService() {
        return new BcryptPasswordEncoderImpl();
    }

    @Bean
    @Scope("singleton")
    public JwtTokenService<Token> jwtTokenService() {
        return new JwtTokenImpl();
    }

    @Bean
    @Scope("singleton")
    public StateService stateService() {
        return new StateImpl();
    }

    @Bean
    @Scope("singleton")
    public CityService cityService() {
        return new CityImpl();
    }

    @Bean
    @Scope("singleton")
    public CepService cepService() {
        return new CepImpl();
    }
}
