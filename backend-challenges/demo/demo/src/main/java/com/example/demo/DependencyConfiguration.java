package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.controllers.UserSecurity;
import com.example.demo.impl.BcryptPasswordEncoderImpl;
import com.example.demo.impl.ExampleImaginaryExponential;
import com.example.demo.impl.ExampleLoginService;
import com.example.demo.impl.ExampleReverseService;
import com.example.demo.impl.JwtTokenImpl;
import com.example.demo.impl.UserImpl;
import com.example.demo.impl.UserSecurityImpl;
import com.example.demo.services.ImaExpService;
import com.example.demo.services.JwtTokenService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.ReverseService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    @Scope("singleton")
    // @Scope("prototype")
    // @Scope("request")
    // @Scope("session")
    public LoginService loginService() {
        return new ExampleLoginService("don", "ferrari");
    }

    @Bean
    @Scope("singleton")
    public ReverseService reverseService() {
        return new ExampleReverseService();
    }

    @Bean
    @Scope("singleton")
    public ImaExpService imReverseService() {
        return new ExampleImaginaryExponential();
    }

    // @Bean
    // @Scope("singleton")
    // public UserService userService() {
    //     return new UserImpl();
    // }

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
    public JwtTokenService jwtTokenService() {
        return new JwtTokenImpl();
    }
}
