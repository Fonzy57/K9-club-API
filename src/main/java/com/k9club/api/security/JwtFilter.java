package com.k9club.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT authentication filter that intercepts each incoming HTTP request to validate the JWT token.
 * <p>
 * Extracts the token from the Authorization header, validates it, retrieves the user details,
 * and sets the authentication information in the Spring Security context.
 * <p>
 * Extends OncePerRequestFilter to ensure the filter is applied only once per request.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

  protected ISecurityUtils securityUtils;
  protected UserDetailsService userDetailsService;

  /**
   * Constructor that injects the security utilities and the user details service.
   *
   * @param securityUtils      utility class for JWT operations
   * @param userDetailsService service to load user-specific data for authentication
   */
  @Autowired
  public JwtFilter(ISecurityUtils securityUtils, UserDetailsService userDetailsService) {
    this.securityUtils = securityUtils;
    this.userDetailsService = userDetailsService;
  }

  /**
   * Filters incoming HTTP requests to authenticate users based on their JWT tokens.
   * <p>
   * Extracts the JWT from the Authorization header, validates it,
   * retrieves the corresponding user details, and sets the authentication
   * into the Spring Security context if the token is valid.
   * <p>
   * If no token is present or the token is invalid, the request proceeds without authentication.
   *
   * @param request     the incoming HttpServletRequest
   * @param response    the outgoing HttpServletResponse
   * @param filterChain the filter chain to pass the request and response to the next filter
   * @throws ServletException if an error occurs during the filtering process
   * @throws IOException      if an I/O error occurs during the filtering process
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // Retrieve the "Authorization" header from the incoming HTTP request
    String token = request.getHeader("Authorization");

    // TODO VOIR SI JE GARDE && token.startsWith("Bearer ")
    // Check if the Authorization header is present
    if (token != null && token.startsWith("Bearer ")) {
      // Remove the "Bearer " prefix to extract the pure JWT token
      String jwt = token.substring(7);

      // Extract the subject (typically the user's email) from the JWT
      String subject = securityUtils.getSubjectFromJwt(jwt);

      // Load the user details using the extracted subject (email)
      UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

      // Create an Authentication object containing the user's credentials and authorities
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      // Add additional details about the authentication request (such as IP address, session ID)
      usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));

      // Set the authentication object in the SecurityContext, marking the user as authenticated
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    // Continue the request through the remaining filters in the chain
    filterChain.doFilter(request, response);

  }


}
