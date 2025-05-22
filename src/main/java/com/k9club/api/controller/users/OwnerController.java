package com.k9club.api.controller.users;

import com.k9club.api.dao.DogDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.user.OwnerUpdateDto;
import com.k9club.api.model.Dog;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.AppUserDetails;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsOwner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users with the OWNER role.
 * <p>
 * - Owners can view and update their own profile and their dogs.
 * - Admins can list and delete any owner accounts.
 * <p>
 * Security annotations:
 * {@code @IsOwner} applies to owner-specific endpoints,
 * {@code @IsAdmin} grants administrative access where required.
 */
@RestController
@CrossOrigin
@IsOwner
public class OwnerController {

  // TODO AJOUTER UN VIEW SPECIFIQUE

  protected UserDao userDao;
  private final DogDao dogDao;

  /**
   * Constructs a new OwnerController with the required DAOs.
   *
   * @param userDao DAO for performing CRUD operations on User entities
   * @param dogDao  DAO for performing CRUD operations on Dog entities
   */
  @Autowired
  public OwnerController(UserDao userDao, DogDao dogDao) {
    this.userDao = userDao;
    this.dogDao = dogDao;
  }

  /**
   * Retrieves all users with the OWNER role.
   * <p>
   * Access restricted to Admin users.
   *
   * @return a {@link ResponseEntity} containing the list of owners and HTTP 200 OK
   */
  @IsAdmin
  @GetMapping("/owners")
  public ResponseEntity<List<User>> getOwners() {
    return new ResponseEntity<>(userDao.findByUserRole(UserRole.OWNER), HttpStatus.OK);
  }

  /**
   * Retrieves a specific owner by their ID.
   * <p>
   * Returns HTTP 404 if no owner exists with the given ID.
   *
   * @param id the ID of the owner to retrieve
   * @return a {@link ResponseEntity} containing the owner and HTTP 200 OK,
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

  /**
   * Retrieves all dogs belonging to the authenticated owner.
   * <p>
   * The current user is obtained via {@code @AuthenticationPrincipal}.
   * Returns HTTP 404 if the user record cannot be found.
   *
   * @param appUserDetails the security principal containing the authenticated user
   * @return a {@link ResponseEntity} containing the list of dogs and HTTP 200 OK,
   * or HTTP 404 Not Found if the user record does not exist
   */
  @GetMapping("/owner/dogs")
  public ResponseEntity<List<Dog>> getOwnerDogs(@AuthenticationPrincipal AppUserDetails appUserDetails) {
    Long ownerId = appUserDetails.getUser().getId();

    List<Dog> dogs = dogDao.findByOwnerId(ownerId);
    System.out.println("DOGS : " + dogs);
    return new ResponseEntity<>(dogs, HttpStatus.OK);
  }

  /**
   * Retrieves a specific dog by its ID for the authenticated owner.
   * <p>
   * Returns:
   * <ul>
   *   <li>404 Not Found if no dog exists with the given ID</li>
   *   <li>403 Forbidden if the dog exists but is not owned by the authenticated user</li>
   *   <li>200 OK with the {@link Dog} entity if the dog is found and owned by the user</li>
   * </ul>
   *
   * @param appUserDetails the security principal containing the authenticated user
   * @param dogId          the ID of the dog to retrieve
   * @return a {@link ResponseEntity} with the dog and appropriate HTTP status
   */
  @GetMapping("/owner/dog/{dogId}")
  public ResponseEntity<Dog> getOwnerDogById(@AuthenticationPrincipal AppUserDetails appUserDetails,
      @PathVariable Long dogId) {
    Long ownerId = appUserDetails.getUser().getId();

    Optional<Dog> optionalDog = dogDao.findById(dogId);
    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Dog existingDog = optionalDog.get();

    if (!existingDog.getOwner().getId().equals(ownerId)) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    return new ResponseEntity<>(existingDog, HttpStatus.OK);
  }


  /**
   * Updates an existing owner's profile information.
   * <p>
   * Only the firstname, lastname, and email fields are modified.
   * Returns:
   * <ul>
   *   <li>404 Not Found if the user does not exist</li>
   *   <li>403 Forbidden if the user exists but is not an OWNER</li>
   *   <li>204 No Content on successful update</li>
   * </ul>
   *
   * @param id             the ID of the owner to update
   * @param ownerUpdateDto the DTO containing new firstname, lastname, and email
   * @return a {@link ResponseEntity} with the appropriate HTTP status
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
   * Deletes an owner user by their ID.
   * <p>
   * Access restricted to Admin users.
   * Returns:
   * <ul>
   *   <li>404 Not Found if the user does not exist</li>
   *   <li>204 No Content on successful deletion</li>
   * </ul>
   * <p>
   * Consider anonymizing data rather than performing a hard delete.
   *
   * @param id the ID of the owner to delete
   * @return a {@link ResponseEntity} with the appropriate HTTP status
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
