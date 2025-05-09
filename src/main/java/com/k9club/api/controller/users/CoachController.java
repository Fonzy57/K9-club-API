package com.k9club.api.controller.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.CoachUpdateDto;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.views.ViewUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing users with the COACH role.
 * <p>
 * Provides endpoints to retrieve, create, update, and delete coach accounts.
 * All operations are restricted to ADMIN users via the @IsAdmin annotation.
 * Uses @JsonView to control which properties are serialized in responses.
 */
@RestController
@CrossOrigin
@IsAdmin
public class CoachController {

  protected UserDao userDao;
  protected PasswordEncoder passwordEncoder;

  /**
   * Constructs a new CoachController with required dependencies.
   *
   * @param userDao         the data access object for {@link User} entities
   * @param passwordEncoder the password encoder used to hash passwords
   */
  @Autowired
  public CoachController(UserDao userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Retrieves a list of all users with the COACH role.
   *
   * @return a ResponseEntity containing the list of coach users and HTTP 200 OK
   */
  @JsonView(ViewUser.Admin.class)
  @GetMapping("/coaches")
  public ResponseEntity<List<User>> getCoaches() {
    return new ResponseEntity<>(userDao.findByUserRole(UserRole.COACH), HttpStatus.OK);
  }

  /**
   * Retrieves a coach user by ID.
   * Returns 404 if no user is found with the given ID.
   *
   * @param id the ID of the coach to retrieve
   * @return a ResponseEntity containing the coach user and HTTP 200 OK, or HTTP 404 Not Found
   */
  @JsonView(ViewUser.Admin.class)
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
   * The role is forcibly set to COACH to ensure data consistency and the password is encoded for security.
   * The password is removed from the returned object for security reasons.
   *
   * @param user the user object containing coach information
   * @return a ResponseEntity with the created coach (without password) and HTTP 201 Created
   */
  @JsonView(ViewUser.Admin.class)
  @PostMapping("/coach")
  public ResponseEntity<User> addCoach(@RequestBody User user) {
    user.setId(null);
    user.setUserRole(UserRole.COACH);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userDao.save(user);

    // Hiding the password before sending the response
    user.setPassword(null);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  /**
   * Updates an existing coach identified by its ID.
   * Only the firstname, lastname and email fields can be modified via the DTO.
   * All other properties (role, creation date, password, etc.) remain unchanged.
   *
   * @param id             the ID of the coach to update
   * @param coachUpdateDto the DTO containing new values for firstname, lastname and email
   * @return ResponseEntity<Void>
   * – 204 No Content if the update succeeds,
   * – 403 Forbidden if the user is not a coach,
   * – 404 Not Found if no user with the given ID exists
   */
  @PutMapping("/coach/{id}")
  public ResponseEntity<Void> updateCoach(@PathVariable Long id, @RequestBody @Valid CoachUpdateDto coachUpdateDto) {
    Optional<User> userOptional = userDao.findById(id);

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User coach = userOptional.get();

    if (coach.getUserRole() != UserRole.COACH) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    coach.setFirstname(coachUpdateDto.getFirstname());
    coach.setLastname(coachUpdateDto.getLastname());
    coach.setEmail(coachUpdateDto.getEmail());

    userDao.save(coach);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    userDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
