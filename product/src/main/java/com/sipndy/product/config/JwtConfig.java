package com.sipndy.product.config;


import com.sipndy.auth.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtUtil jwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiry.minutes}") long expiryMinutes
    ) {
        return new JwtUtil(secret, expiryMinutes);
    }
}
