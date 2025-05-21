package com.k9club.api.controller.users;

import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.user.OwnerUpdateDto;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsOwner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing users with the OWNER role.
 * <p>
 * Provides endpoints to list, retrieve, update, and delete owner accounts.
 * Access is restricted via {@code @IsOwner} on the class and {@code @IsAdmin}
 * on methods where appropriate.
 */
@RestController
@CrossOrigin
@IsOwner
public class OwnerController {
  
  // TODO AJOUTER UN VIEW SPECIFIQUE

  protected UserDao userDao;

  /**
   * Constructor injecting the UserDao dependency.
   *
   * @param userDao the DAO used to interact with User entities in the database
   */
  @Autowired
  public OwnerController(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Retrieves a list of all users with the OWNER role.
   * Only accessible by ADMIN users.
   *
   * @return a ResponseEntity containing the list of owner users and HTTP 200 OK
   */
  @IsAdmin
  @GetMapping("/owners")
  public ResponseEntity<List<User>> getOwners() {
    return new ResponseEntity<>(userDao.findByUserRole(UserRole.OWNER), HttpStatus.OK);
  }

  /**
   * Retrieves a specific owner user by ID.
   * Returns HTTP 404 if no user exists with the given ID.
   *
   * @param id the ID of the owner to retrieve
   * @return a ResponseEntity containing the owner and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @GetMapping("/owner/{id}")
  public ResponseEntity<User> getOwnerById(@PathVariable Long id) {
    Optional<User> optionalUser = userDao.findById(id);

    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
  }

  //------------------------------------------------------------------------------------------------
  // TODO AJOUTER LA ROUTE /owner/{id}/dogs pour récupérer la liste des chiens d'un utilisateur
  //------------------------------------------------------------------------------------------------

  //------------------------------------------------------------------------------------------------
  // TODO AJOUTER LA ROUTE /owner/{id}/dog/{id} pour récupérer un chien d'un utilisateur
  //------------------------------------------------------------------------------------------------


  /**
   * Updates firstname, lastname, and email of an existing owner user.
   * Returns HTTP 404 if the user is not found, or HTTP 403 if the user exists
   * but does not have the OWNER role.
   *
   * @param id             the ID of the owner to update
   * @param ownerUpdateDto the DTO containing new firstname, lastname, and email
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * HTTP 404 Not Found or HTTP 403 Forbidden on failure
   */
  @PutMapping("/owner/{id}")
  public ResponseEntity<Void> updateOwner(@PathVariable Long id, @RequestBody @Valid OwnerUpdateDto ownerUpdateDto) {
    Optional<User> optionalUser = userDao.findById(id);

    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    User owner = optionalUser.get();

    if (owner.getUserRole() != UserRole.OWNER) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    owner.setFirstname(ownerUpdateDto.getFirstname());
    owner.setLastname(ownerUpdateDto.getLastname());
    owner.setEmail(ownerUpdateDto.getEmail());

    userDao.save(owner);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes an owner user by ID.
   * Performs a hard delete; consider anonymizing data instead of permanent removal.
   * Returns HTTP 404 if the user does not exist.
   *
   * @param id the ID of the owner to delete
   * @return a ResponseEntity with HTTP 204 No Content if deleted,
   * or HTTP 404 Not Found if the user does not exist
   */
  @IsAdmin
  @DeleteMapping("/owner/{id}")
  public ResponseEntity<Void> deleteOwnerById(@PathVariable Long id) {
    //

    //  TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES

    //

    Optional<User> optionalUser = userDao.findById(id);
    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    userDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
