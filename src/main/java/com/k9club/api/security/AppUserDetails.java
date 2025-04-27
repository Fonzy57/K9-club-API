package com.k9club.api.security;

import com.k9club.api.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of the UserDetails interface used by Spring Security.
 * <p>
 * Wraps the application's User entity and provides user-specific information
 * such as authorities, username, and password needed for authentication and authorization.
 */
@Getter
public class AppUserDetails implements UserDetails {

  protected User user;

  public AppUserDetails(User user) {
    this.user = user;
  }

  /**
   * Return the authorities granted to the user.
   * <p>
   * It converts the user's role into a SimpleGrantedAuthority
   * by prefixing it with "ROLE_".
   *
   * @return a collection containing the user's granted authorities
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name()));
  }

  /**
   * Returns the username used to authenticate the user.
   * In this case, it returns the user's email address.
   *
   * @return the user's email as username
   */
  @Override
  public String getUsername() {
    return user.getEmail();
  }

  /**
   * Returns the password used to authenticate the user.
   * This password should already be encoded.
   *
   * @return the user's password
   */
  @Override
  public String getPassword() {
    return user.getPassword();
  }
}
