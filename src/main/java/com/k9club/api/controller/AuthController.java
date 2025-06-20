package com.k9club.api.controller;

import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.LoginRequestDto;
import com.k9club.api.dto.user.OwnerRegistrationDto;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.AppUserDetails;
import com.k9club.api.security.interfaces.ISecurityUtils;
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
   * Registers a new owner user in the system.
   * <p>
   * Constructs a {@link User} from the provided DTO, encodes the password,
   * and assigns the OWNER role. Persists the new user to the database.
   * <p>
   * Does not return any content in the response (HTTP 204 No Content).
   *
   * @param ownerRegistrationDto the DTO containing firstname, lastname, email, and raw password
   * @return a {@link ResponseEntity} with HTTP 204 No Content on successful registration
   */
  @PostMapping("/registration")
  public ResponseEntity<Void> inscription(@RequestBody @Valid OwnerRegistrationDto ownerRegistrationDto) {
    User newUser = new User();
    newUser.setFirstname(ownerRegistrationDto.getFirstname());
    newUser.setLastname(ownerRegistrationDto.getLastname());
    newUser.setEmail(ownerRegistrationDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(ownerRegistrationDto.getPassword()));
    newUser.setUserRole(UserRole.OWNER);

    // Saving the user in the database
    userDao.save(newUser);

    // Hiding the password before sending the response
    // newUser.setPassword(null);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Authenticates a user based on provided credentials and generates a JWT token if authentication succeeds.
   * <p>
   * Receives login credentials (email and password) through a LoginRequestDTO object.
   * Attempts to authenticate the user using the authentication provider.
   * If authentication is successful, a JWT token is generated and returned.
   * If authentication fails, an HTTP 401 Unauthorized status is returned without a token.
   *
   * @param loginRequest the DTO containing the user's login credentials (email and password)
   * @return a ResponseEntity containing the JWT token with HTTP 200 OK status if successful,
   * or HTTP 401 Unauthorized if authentication fails
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequest) {
    try {
      AppUserDetails userDetails = (AppUserDetails) authenticationProvider.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(),
                  loginRequest.getPassword()
              ))
          .getPrincipal();

      return new ResponseEntity<>(securityUtils.generateToken(userDetails), HttpStatus.OK);

    } catch (AuthenticationException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
