package com.k9club.api.security;

import com.k9club.api.security.interfaces.ISecurityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utility service responsible for security-related operations,
 * including JWT token generation, extraction of user roles, and parsing JWTs.
 * <p>
 * Implements the ISecurityUtils interface.
 */
@Service
public class SecurityUtils implements ISecurityUtils {

  /**
   * Secret key used for signing and verifying JWT tokens.
   * Injected from application properties.
   */
  @Value("${JWT_SECRET}")
  private String jwtSecret;

  /**
   * Retrieves the user's role from their granted authorities.
   * <p>
   * Extracts the first authority available and returns its name.
   *
   * @param userDetails the user details containing the authorities
   * @return the name of the user's role, or null if no role is found
   */
  @Override
  public String getRole(AppUserDetails userDetails) {
    return userDetails.getAuthorities()
        .stream()
        .map(role -> role.getAuthority())
        .findFirst()
        .orElse(null);
  }

  /**
   * Generates a JWT token for the authenticated user.
   * <p>
   * Gathers user-specific claims such as role, id, firstname, and lastname,
   * then signs and builds the token using the HS256 algorithm.
   *
   * @param userDetails the authenticated user's details
   * @return a signed JWT token containing user information
   */
  @Override
  public String generateToken(AppUserDetails userDetails) {
    // Map containing user information to be included as claims in the JWT token
    Map<String, Object> userInfosToClaim = new LinkedHashMap<>();
    userInfosToClaim.put("id", userDetails.getUser().getId());
    userInfosToClaim.put("firstname", userDetails.getUser().getFirstname());
    userInfosToClaim.put("lastname", userDetails.getUser().getLastname());
    userInfosToClaim.put("role", getRole(userDetails));

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .addClaims(userInfosToClaim)
        .signWith(SignatureAlgorithm.HS256, this.jwtSecret)
        // TODO VOIR PAGE 486 POUR L'EXPIRATION DU TOKEN
        // PAGE 520
        .compact();
  }

  /**
   * Extracts the subject (typically the username or email) from a given JWT token.
   * <p>
   * Parses the token using the secret key and retrieves the subject from its claims.
   *
   * @param jwt the JWT token from which to extract the subject
   * @return the subject contained within the JWT token
   */
  @Override
  public String getSubjectFromJwt(String jwt) {
    return Jwts.parser()
        .setSigningKey(this.jwtSecret)
        .parseClaimsJws(jwt)
        .getBody()
        .getSubject();
  }
}
