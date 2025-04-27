package com.k9club.api;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class ApiApplication {

  /**
   * Main method to launch the Spring Boot application.
   *
   * @param args command-line arguments passed at startup
   */
  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

  /**
   * Initializes application-wide settings after dependency injection is done.
   * In this case, it sets the default time zone to UTC.
   */
  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  /**
   * Defines a PasswordEncoder bean using BCrypt hashing algorithm.
   * This bean is used to encode and verify user passwords securely.
   *
   * @return a PasswordEncoder instance based on BCrypt
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
