package com.k9club.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecurityUtils implements ISecurityUtils {

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Override
  public String getRole(AppUserDetails userDetails) {
    return userDetails.getAuthorities()
        .stream()
        .map(role -> role.getAuthority())
        .findFirst()
        .orElse(null);
  }

  @Override
  public String generateToken(AppUserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .addClaims(Map.of("role", getRole(userDetails)))
        .signWith(SignatureAlgorithm.HS256, this.jwtSecret)
        // TODO VOIR PAGE 486 POUR L'EXPIRATION DU TOKEN
        .compact();
  }

  @Override
  public String getSubjectFromJwt(String jwt) {
    return Jwts.parser()
        .setSigningKey(this.jwtSecret)
        .parseClaimsJws(jwt)
        .getBody()
        .getSubject();
  }
}
