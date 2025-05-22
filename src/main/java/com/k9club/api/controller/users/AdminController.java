package com.k9club.api.controller.users;

import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.user.AdminUpdateDto;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.annotations.IsAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing users with the ADMIN role.
 * <p>
 * Provides endpoints to retrieve and update admin accounts.
 * All operations are restricted to ADMIN users via the {@code @IsAdmin} annotation.
 * Uses {@code @JsonView} to control which user fields are included in responses.
 */
@RestController
@CrossOrigin
@IsAdmin
public class AdminController {
  protected UserDao userDao;

  /**
   * Constructor injecting the UserDao dependency.
   *
   * @param userDao the DAO used to interact with User entities in the database
   */
  @Autowired
  public AdminController(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Retrieves a list of all users with the ADMIN role.
   *
   * @return a ResponseEntity containing the list of admin users and HTTP 200 OK
   */
  @GetMapping("/admins")
  public ResponseEntity<List<User>> getAdmins() {
    return new ResponseEntity<>(userDao.findByUserRole(UserRole.ADMIN), HttpStatus.OK);
  }

  /**
   * Retrieves a single admin user by ID.
   * Returns 404 if no user with the given ID exists.
   *
   * @param id the ID of the admin user to retrieve
   * @return a ResponseEntity containing the admin user and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @GetMapping("/admin/{id}")
  public ResponseEntity<User> getAdmin(@PathVariable Long id) {
    Optional<User> optionalUser = userDao.findByIdAndUserRole(id, UserRole.ADMIN);
    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
  }

  /**
   * Updates an existing admin user’s firstname, lastname, and email.
   * Returns 404 if no user with the given ID exists.
   *
   * @param id             the ID of the admin to update
   * @param adminUpdateDto the DTO containing new values for firstname, lastname, and email
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the admin does not exist
   */
  @PutMapping("/admin/{id}")
  public ResponseEntity<Void> updateAdmin(@PathVariable Long id, @RequestBody @Valid AdminUpdateDto adminUpdateDto) {
    Optional<User> userOptional = userDao.findByIdAndUserRole(id, UserRole.ADMIN);

    if (userOptional.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User admin = userOptional.get();
    admin.setFirstname(adminUpdateDto.getFirstname());
    admin.setLastname(adminUpdateDto.getLastname());
    admin.setEmail(adminUpdateDto.getEmail());

    userDao.save(admin);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
