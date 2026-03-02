package it.objectmethod.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("it.objectmethod.demo.spring.repository")
@EntityScan("it.objectmethod.demo.spring.models")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}