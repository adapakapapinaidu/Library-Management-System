import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() // Enable CORS
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").permitAll()   // Allow all endpoints
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // Allow preflight OPTIONS requests
        return http.build();
    }
}
