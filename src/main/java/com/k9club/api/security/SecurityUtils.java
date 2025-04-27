package com.k9club.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecurityUtils implements ISecurityUtils {

  // TODO AJOUTER SECRET DANS ENV

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
        .signWith(SignatureAlgorithm.HS256, "azerty") // TODO CHANGER AVEC LE SECRET DU ENV
        .compact();
  }

  @Override
  public String getSubjectFromJwt(String jwt) {
    return Jwts.parser()
        .setSigningKey("azerty") // TODO CHANGER AVEC LE SECRET DU ENV
        .parseClaimsJws(jwt)
        .getBody()
        .getSubject();
  }
}
