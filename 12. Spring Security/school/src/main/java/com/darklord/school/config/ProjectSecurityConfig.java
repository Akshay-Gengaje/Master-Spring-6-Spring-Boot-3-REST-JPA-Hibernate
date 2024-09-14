package com.darklord.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // THIS WILL PERMIT ALL APIS
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()  // All requests are permitted without authentication
                );

        return http.build();
    }
}
