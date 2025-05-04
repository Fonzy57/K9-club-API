package com.k9club.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.UserDao;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.views.ViewUserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing users with the COACH role.
 * <p>
 * Provides endpoints to retrieve, create, update, and delete coach accounts.
 * All endpoints are restricted to ADMIN users through the @IsAdmin annotation.
 * Uses @JsonView to filter the data returned to clients.
 */
@RestController
@CrossOrigin
@IsAdmin
public class CoachController {

  protected UserDao userDao;

  /**
   * Constructor injecting the UserDao dependency.
   *
   * @param userDao the DAO used to interact with the User entity in the database
   */
  @Autowired
  public CoachController(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Retrieves a list of all users with the COACH role.
   *
   * @return a ResponseEntity containing the list of coach users and HTTP 200 OK
   */
  @JsonView(ViewUserAdmin.class)
  @GetMapping("/coaches")
  public ResponseEntity<List<User>> getCoaches() {
    return new ResponseEntity<>(userDao.findByUserRole(UserRole.COACH), HttpStatus.OK);
  }

  /**
   * Retrieves a coach user by ID.
   *
   * @param id the ID of the coach to retrieve
   * @return a ResponseEntity containing the coach user if found, or HTTP 404 if not found
   */
  @JsonView(ViewUserAdmin.class)
  @GetMapping("/coach/{id}")
  public ResponseEntity<User> getCoach(@PathVariable Long id) {
    Optional<User> optionalUser = userDao.findById(id);
    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
  }

  /**
   * Creates a new coach user.
   * The role is forcibly set to COACH to ensure data consistency.
   * The password is removed from the returned object for security reasons.
   *
   * @param user the user object containing coach information
   * @return a ResponseEntity with the created coach and HTTP 201 Created
   */
  @JsonView(ViewUserAdmin.class)
  @PostMapping("/coach")
  public ResponseEntity<User> addCoach(@RequestBody User user) {
    user.setId(null);
    user.setUserRole(UserRole.COACH);

    userDao.save(user);

    // Hiding the password before sending the response
    user.setPassword(null);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  /**
   * Updates an existing coach user by ID.
   * Only users with the COACH role can be updated through this endpoint.
   * Role and creation date cannot be modified.
   *
   * @param id   the ID of the coach to update
   * @param user the updated data for the coach
   * @return a ResponseEntity with HTTP 204 No Content if successful,
   * 403 Forbidden if the user is not a coach, or 404 Not Found if the user does not exist
   */
  @PutMapping("/coach/{id}")
  public ResponseEntity<Void> updateCoach(@PathVariable Long id, @RequestBody User user) {
    Optional<User> userOptional = userDao.findById(id);

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User coach = userOptional.get();

    if (coach.getUserRole() != UserRole.COACH) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    coach.setId(id);
    coach.setFirstname(user.getFirstname());
    coach.setLastname(user.getLastname());
    coach.setEmail(user.getEmail());
    coach.setPassword(user.getPassword());

    // User cannot change creation date
    // Preserve the original creation date
    coach.setCreatedAt(userOptional.get().getCreatedAt());
    // User cannot change role
    coach.setUserRole(userOptional.get().getUserRole());


    userDao.save(coach);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a coach user by ID.
   *
   * @param id the ID of the coach to delete
   * @return a ResponseEntity with HTTP 204 No Content if deleted, or 404 Not Found if the user does not exist
   */
  @DeleteMapping("/coach/{id}")
  public ResponseEntity<User> deleteCoach(@PathVariable Long id) {
    Optional<User> userOptional = userDao.findById(id);

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES
    userDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
