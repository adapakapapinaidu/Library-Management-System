package it.objectmethod.demo.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable() // Disable CSRF for APIs (token-based)
            .cors() // Enable CORS
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session, token only
            .and()
            .authorizeHttpRequests()
                .requestMatchers("/users/login", "/users/register").permitAll() // Allow public endpoints
                .anyRequest().authenticated() // Other endpoints require authentication
            .and()
            .httpBasic(); // Optional: basic auth for testing

        return http.build();
    }
}
