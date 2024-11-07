package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.dto.Token;
import com.example.demo.filters.JWTAuthenticationFilter;
import com.example.demo.services.JwtTokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    JwtTokenService<Token> service;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/**").permitAll()
            .requestMatchers("/product").authenticated()
                // .anyRequest()
                // .anonymous()
            )
            // .rememberMe(Customizer.withDefaults())
            .addFilterBefore(new JWTAuthenticationFilter(service), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}