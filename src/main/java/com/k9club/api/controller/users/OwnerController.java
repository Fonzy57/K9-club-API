package com.k9club.api.controller.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.CourseDao;
import com.k9club.api.dao.CourseRegistrationDao;
import com.k9club.api.dao.DogDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.user.OwnerUpdateDto;
import com.k9club.api.jsonview.ViewsUser;
import com.k9club.api.model.CourseRegistration;
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
 * REST controller for operations available to users with the OWNER role.
 * <p>
 * - Owners can view and update their own profile, list and retrieve their dogs,
 * and fetch all course registrations associated with their dogs.
 * - Admin users may list and delete any owner accounts.
 * <p>
 * Security:
 * {@code @IsOwner} applies to all methods by default, allowing only owners;
 * {@code @IsAdmin} grants administrative access on specific endpoints.
 */
@RestController
@CrossOrigin
@IsOwner
public class OwnerController {

  protected UserDao userDao;
  private final DogDao dogDao;
  private final CourseRegistrationDao courseRegistrationDao;
  private final CourseDao courseDao;

  /**
   * Constructs a new OwnerController with required DAOs.
   *
   * @param userDao               DAO for user CRUD operations
   * @param dogDao                DAO for dog CRUD operations
   * @param courseRegistrationDao DAO for course registration CRUD operations
   * @param courseDao             DAO for course CRUD operations
   */
  @Autowired
  public OwnerController(UserDao userDao, DogDao dogDao, CourseRegistrationDao courseRegistrationDao, CourseDao courseDao) {
    this.userDao = userDao;
    this.dogDao = dogDao;
    this.courseRegistrationDao = courseRegistrationDao;
    this.courseDao = courseDao;
  }

  /**
   * Retrieves all users with the OWNER role.
   * <p>
   * Access restricted to Admin users.
   *
   * @return a {@link ResponseEntity} containing the list of owners and HTTP 200 OK
   */
  @IsAdmin
  @JsonView(ViewsUser.Admin.class)
  @GetMapping("/owners")
  public ResponseEntity<List<User>> getOwners() {
    return new ResponseEntity<>(userDao.findByUserRole(UserRole.OWNER), HttpStatus.OK);
  }

  /**
   * Retrieves a specific owner by their ID.
   * <p>
   * Returns 404 if the owner does not exist.
   *
   * @param id the owner’s user ID
   * @return the owner user with HTTP 200 OK, or HTTP 404 Not Found
   */
  @JsonView(ViewsUser.Owner.class)
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
   * Uses {@code @AuthenticationPrincipal} to obtain the current user's ID.
   * Returns HTTP 404 if the user record is not found.
   *
   * @param appUserDetails security principal of the authenticated user
   * @return list of dogs owned by the user with HTTP 200 OK,
   * or HTTP 404 Not Found if the user does not exist
   */
  @JsonView(ViewsUser.Owner.class)
  @GetMapping("/owner/dogs")
  public ResponseEntity<List<Dog>> getOwnerDogs(@AuthenticationPrincipal AppUserDetails appUserDetails) {
    Long ownerId = appUserDetails.getUser().getId();

    List<Dog> dogs = dogDao.findByOwnerId(ownerId);
    return new ResponseEntity<>(dogs, HttpStatus.OK);
  }

  /**
   * Retrieves a specific dog by its ID for the authenticated owner.
   * <p>
   * Returns:
   * <ul>
   *   <li>404 Not Found if the dog does not exist</li>
   *   <li>403 Forbidden if the dog is not owned by the user</li>
   *   <li>200 OK with the dog details otherwise</li>
   * </ul>
   *
   * @param appUserDetails security principal of the authenticated user
   * @param dogId          the ID of the dog to retrieve
   * @return the dog with HTTP 200 OK, or appropriate error status
   */
  @JsonView(ViewsUser.Owner.class)
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
   * Updates the authenticated owner's profile information.
   * <p>
   * Allows changes to firstname, lastname, and email only.
   * Returns:
   * <ul>
   *   <li>404 Not Found if the user does not exist</li>
   *   <li>403 Forbidden if the user exists but is not an OWNER</li>
   *   <li>204 No Content on successful update</li>
   * </ul>
   *
   * @param id             the ID of the owner to update
   * @param ownerUpdateDto DTO containing new firstname, lastname, and email
   * @return appropriate HTTP status with no body
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
   * Returns HTTP 404 if the user does not exist; otherwise deletes and returns HTTP 204 No Content.
   *
   * @param id the ID of the owner to delete
   * @return appropriate HTTP status with no body
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

  /**
   * Retrieves all course registrations across all dogs owned by the authenticated owner.
   * <p>
   * Uses a custom DAO method to fetch registrations by dog owner ID.
   *
   * @param appUserDetails security principal of the authenticated user
   * @return list of CourseRegistration entities with HTTP 200 OK
   */
  @JsonView(ViewsUser.Owner.class)
  @GetMapping("/owner/dogs/registrations")
  public ResponseEntity<List<CourseRegistration>> getDogsOwnerCourseRegistrations(@AuthenticationPrincipal AppUserDetails appUserDetails) {
    Long ownerId = appUserDetails.getUser().getId();

    List<CourseRegistration> registrations = courseRegistrationDao.findByDogOwnerId(ownerId);

    return new ResponseEntity<>(registrations, HttpStatus.OK);
  }

  // TODO FAIRE UNE ROUTE COMME AU DESSUS MAIS EN NE RÉCUPÉRANT UNIQUEMENT LES COURS QUI SONT
  // A PARTIR DE LA DATE DU JOUR ET A VENIR (startDate >= today)
}
