package com.k9club.api.security;

import com.k9club.api.dao.UserDao;
import com.k9club.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service responsible for retrieving user details for authentication.
 * <p>
 * Implements the UserDetailsService interface required by Spring Security,
 * fetching user information from the database based on the provided email.
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

  protected UserDao userDao;

  /**
   * Constructor that injects the UserDao dependency.
   *
   * @param userDao the data access object used to retrieve users from the database
   */
  @Autowired
  public AppUserDetailsService(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Loads the user's details by email during the authentication process.
   * <p>
   * Searches the database for a user with the given email.
   * If no user is found, throw a UsernameNotFoundException.
   * Otherwise, wraps the user entity into an AppUserDetails object.
   *
   * @param email the email identifying the user whose data is required
   * @return a UserDetails object containing the user's authentication information
   * @throws UsernameNotFoundException if no user is found with the given email
   */
  public UserDetails loadUserByUsername(String email) {
    Optional<User> optionalUser = userDao.findByEmail(email);

    if (optionalUser.isEmpty()) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }

    return new AppUserDetails(optionalUser.get());
  }

}
