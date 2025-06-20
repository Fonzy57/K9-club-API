package com.k9club.api.controller.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.UserDao;
import com.k9club.api.jsonview.ViewsUser;
import com.k9club.api.model.User;
import com.k9club.api.security.AppUserDetails;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsOwner;
import com.k9club.api.security.annotations.IsSuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing user-related operations.
 * <p>
 * Provides endpoints to retrieve, create, update, and delete users,
 * with access restricted based on user roles (ADMIN and SUPER_ADMIN).
 * Uses @JsonView to control the JSON response according to the user's role.
 */
@RestController
@CrossOrigin
@IsAdmin
public class UserController {

  protected UserDao userDao;

  /**
   * Constructor injecting the UserDao dependency.
   *
   * @param userDao the DAO used to interact with the User entity in the database
   */
  @Autowired
  public UserController(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Retrieves the currently authenticated user’s own profile information.
   * Returns HTTP 404 if no user record exists for the authenticated user.
   *
   * @param userDetails the authentication principal representing the current user
   * @return a ResponseEntity containing the User data and HTTP 200 OK,
   * or HTTP 404 Not Found if the user does not exist
   */

  //

  // TODO    ICI NE RENVOYER QUE LES INFORMATIONS DE L'UTILISATEUR
  // TODO    PAS LES CHIENS OU AUTRE, FAIRE UN JSON VIEW

  //
  @IsOwner
  @JsonView(ViewsUser.Info.class)
  @GetMapping("/user/me")
  public ResponseEntity<User> getUserInformation(@AuthenticationPrincipal AppUserDetails userDetails) {
    Long id = userDetails.getUser().getId();

    Optional<User> optionalUser = userDao.findById(id);
    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User user = optionalUser.get();

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  /**
   * Retrieves a list of all users with a view adapted for ADMIN users.
   *
   * @return a ResponseEntity containing the list of users and an HTTP 200 OK status
   */
  @JsonView(ViewsUser.Admin.class)
  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
  }

  /**
   * Retrieves a specific user by ID with a view adapted for ADMIN users.
   *
   * @param id the ID of the user to retrieve
   * @return a ResponseEntity containing the user if found with HTTP 200 OK,
   * or HTTP 404 Not Found if the user does not exist
   */
  @JsonView(ViewsUser.Admin.class)
  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    Optional<User> user = userDao.findById(id);

    if (user.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.OK);
  }

  /**
   * Retrieves a list of all users with a view adapted for SUPER_ADMIN users.
   * <p>
   * Provides more detailed information than the ADMIN view.
   *
   * @return a ResponseEntity containing the list of users and an HTTP 200 OK status
   */
  @IsSuperAdmin
  @JsonView(ViewsUser.Admin.class)
  @GetMapping("/super-admin/users")
  public ResponseEntity<List<User>> getUsersForSuperAdmin() {
    return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
  }

  /**
   * Retrieves a specific user by ID with a view adapted for SUPER_ADMIN users.
   *
   * @param id the ID of the user to retrieve
   * @return a ResponseEntity containing the user if found with HTTP 200 OK,
   * or HTTP 404 Not Found if the user does not exist
   */
  @IsSuperAdmin
  @JsonView(ViewsUser.Admin.class)
  @GetMapping("/super-admin/user/{id}") // TODO VOIR PAGE 507 POUR PROTEGER LES ROUTES /super-admin POUR LE ROLE
  public ResponseEntity<User> getUserForSuperAdmin(@PathVariable Long id) {
    Optional<User> user = userDao.findById(id);

    if (user.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.OK);
  }

  /**
   * Deletes a user by ID.
   * <p>
   * Only accessible by SUPER_ADMIN users.
   *
   * @param id the ID of the user to delete
   * @return a ResponseEntity with HTTP 204 No Content if successful,
   * or HTTP 404 Not Found if the user does not exist
   */
  @IsSuperAdmin
  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    Optional<User> user = userDao.findById(id);
    if (user.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //

    // TODO VOIR POUR ANONYMISATION DES DONNEES

    //

    userDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Creates a new user.
   * <p>
   * Only accessible by SUPER_ADMIN users.
   *
   * @param user the user object to be created
   * @return a ResponseEntity with HTTP 201 Created status
   */
  @IsSuperAdmin
  @PostMapping("/user")
  public ResponseEntity<String> addUser(@RequestBody User user) {
    user.setId(null);
    userDao.save(user);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Updates an existing user by ID.
   * <p>
   * Only accessible by SUPER_ADMIN users.
   * The creation date (createdAt) cannot be modified.
   *
   * @param id   the ID of the user to update
   * @param user the updated user information
   * @return a ResponseEntity with HTTP 204 No Content if successful,
   * or HTTP 404 Not Found if the user does not exist
   */
  @IsSuperAdmin
  @PutMapping("/user/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    Optional<User> userOptional = userDao.findById(id);

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    user.setId(id);

    // User cannot change creation date
    // Preserve the original creation date
    user.setCreatedAt(userOptional.get().getCreatedAt());

    userDao.save(user);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
