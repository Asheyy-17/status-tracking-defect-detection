package com.example.status_tracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.disable()) // ✅ Corrected for Spring Security 6.x
            .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF if not needed
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // ✅ Allow API access
                .anyRequest().permitAll() // ✅ Allow all other requests
            );

        return http.build();
    }
}
