package it.objectmethod.demo.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()    // enable CORS
            .and()
            .csrf().disable()  // disable CSRF for API calls
            .authorizeHttpRequests()
            .requestMatchers("/**").permitAll(); // allow all requests
        return http.build();
    }
}
