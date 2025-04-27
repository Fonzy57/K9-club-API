package com.k9club.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

  protected PasswordEncoder passwordEncoder;
  protected UserDetailsService userDetailsService;
  protected JwtFilter jwtFilter;

  @Autowired
  public SecurityConfiguration(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, JwtFilter jwtFilter) {
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.csrf(config -> config.disable())
        .cors(config -> config.configurationSource(corsConfigurationSource()))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /**
   * Defines and configures the authentication provider for the application.
   * <p>
   * This provider uses a DAO-based authentication mechanism, setting the
   * password encoder and the user details service to retrieve user information
   * and verify credentials.
   *
   * @return an AuthenticationProvider configured with a PasswordEncoder and a UserDetailsService
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

    // Inversion control
    auth.setPasswordEncoder(passwordEncoder); // Encoding the password
    auth.setUserDetailsService(userDetailsService); // Explain how we get a user

    return auth;
  }


  /**
   * Configures and returns a CORS (Cross-Origin Resource Sharing) configuration source.
   * <p>
   * This configuration allows all origins ("*"), and permits HTTP methods such as
   * GET, POST, DELETE, PUT, and PATCH. It also allows all headers.
   * <p>
   * The configuration is registered for all endpoints (/**).
   *
   * @return a CorsConfigurationSource with the specified CORS settings
   */
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(List.of("*"));
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "PATCH"));

    // TODO VOIR PDF PAGE 417 ET PRECISER LES HEADERS
    corsConfiguration.setAllowedHeaders(List.of("*"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }


}
