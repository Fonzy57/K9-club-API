package com.k9club.api.controller;

import com.k9club.api.dao.UserDao;
import com.k9club.api.model.User;
import com.k9club.api.security.AppUserDetails;
import com.k9club.api.security.ISecurityUtils;
import com.k9club.api.security.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling authentication-related endpoints.
 * <p>
 * Provides functionality to register (sign up) new users into the system.
 */
@CrossOrigin
@RestController
public class AuthController {

  protected UserDao userDao;
  protected PasswordEncoder passwordEncoder;
  protected AuthenticationProvider authenticationProvider;
  protected ISecurityUtils securityUtils;

  /**
   * Constructor that injects the required dependencies for authentication operations.
   *
   * @param userDao                the data access object to manage user entities
   * @param passwordEncoder        the password encoder used to securely hash user passwords
   * @param authenticationProvider the authentication provider used for authentication processes
   * @param securityUtils          utility service for security-related operations
   */
  @Autowired
  public AuthController(UserDao userDao,
      PasswordEncoder passwordEncoder,
      AuthenticationProvider authenticationProvider,
      ISecurityUtils securityUtils) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.authenticationProvider = authenticationProvider;
    this.securityUtils = securityUtils;
  }

  /**
   * Registers a new user into the system.
   * <p>
   * The user's role is automatically set to USER by default.
   * The password is securely encoded before saving the user into the database.
   * <p>
   * After saving, the password is set to null to avoid exposing it in the response.
   *
   * @param user the user object to be registered (validated from request body)
   * @return a ResponseEntity containing the created user (without password) and HTTP 201 status
   */
  @PostMapping("/registration")
  public ResponseEntity<User> inscription(@RequestBody @Valid User user) {
    user.setRole(Role.USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Saving the user in the database
    userDao.save(user);

    // Hiding the password before sending the response
    user.setPassword(null);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  // TODO AJOUTER UNE ROUTE POUR L'AJOUT D'UN COACH AVEC UN ROLE PARTICULIER
  // SEUL UN ADMIN PEUT APPELER CETTE ROUTE

  /**
   * Authenticates a user and generates a JWT token upon successful login.
   * <p>
   * Attempts to authenticate the user using the provided email and password.
   * If authentication is successful, a JWT token is generated and returned.
   * If authentication fails, an HTTP 401 Unauthorized status is returned.
   *
   * @param user the user object containing the login credentials (email and password)
   * @return a ResponseEntity containing the JWT token with HTTP 200 OK status if successful,
   * or HTTP 401 Unauthorized if authentication fails
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Valid User user) {
    try {
      AppUserDetails userDetails = (AppUserDetails) authenticationProvider.authenticate(
              new UsernamePasswordAuthenticationToken(
                  user.getEmail(),
                  user.getPassword()
              ))
          .getPrincipal();

      return new ResponseEntity<>(securityUtils.generateToken(userDetails), HttpStatus.OK);

    } catch (AuthenticationException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
