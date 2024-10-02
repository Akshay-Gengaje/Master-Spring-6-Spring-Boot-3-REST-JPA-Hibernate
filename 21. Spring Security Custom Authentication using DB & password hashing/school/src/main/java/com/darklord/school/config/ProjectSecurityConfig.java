package com.darklord.school.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // THIS WILL PERMIT ALL APIS
        /*
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()  // All requests are permitted without authentication
                );
         */

        // This is custom security
        http
                .csrf(csrf ->
                        csrf
                                .ignoringRequestMatchers("/saveMsg")
                                .ignoringRequestMatchers("/public/**")
                )

                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/displayMessages").hasRole("ADMIN")
                        .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/", "/home", "/holidays/**", "/contact", "/about", "/login", "/assets/**", "/saveMsg", "/logout").permitAll()
                        .requestMatchers("/courses").authenticated()
                        .requestMatchers("/public/**").permitAll()
                ).formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/dashboard")
                                .failureUrl("/login?error=true")
                                .permitAll())
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login?logout=true")  // Fixed logout URL
                                .invalidateHttpSession(true)
                                .permitAll()
                );

        // THIS WILL DENY ALL APIS
        /*
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().denyAll()  // All requests are permitted without authentication
                );
         */
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("12345").roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
