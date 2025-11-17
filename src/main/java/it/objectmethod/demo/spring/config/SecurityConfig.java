package it.objectmethod.demo.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()    // Enable CORS
            .and()
            .csrf().disable()
            .authorizeRequests()  // <-- Spring Boot 2.x uses authorizeRequests()
            .antMatchers("/**").permitAll(); // Use antMatchers instead of requestMatchers
        return http.build();
    }
}
