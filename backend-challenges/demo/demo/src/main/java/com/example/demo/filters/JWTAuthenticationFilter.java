package com.example.demo.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.dto.Token;
import com.example.demo.services.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    final JwtTokenService<Token> JwtTokenService;
    public JWTAuthenticationFilter(JwtTokenService<Token> JwtTokenService) {
        this.JwtTokenService = JwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var jwt = getJwt(request);

        if (jwt == null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        var token = JwtTokenService.validate(jwt);
        if (token == null)
        {
            filterChain.doFilter(request, response);
            return;
        }
        
        var authentication = new UsernamePasswordAuthenticationToken("jao", null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.setAttribute("token", token);
        filterChain.doFilter(request, response);
    }
    
    // String getJwt(HttpServletRequest request) throws IOException {
        
    //     var reader = request.getReader();
    //     var value = reader.readLine().split(",")[0].split(":")[1].replace("\"", "");
    //     reader.close();

    //     return value;
    // }

    String getJwt(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
