package com.k9club.api.controller;

import com.k9club.api.dao.DogDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.model.Dog;
import com.k9club.api.model.User;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsOwner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Dog entities.
 * <p>
 * Owners may list and modify only their own dogs; Admins can list and view any dog.
 * Uses {@code @IsOwner} at the class level and {@code @IsAdmin} on methods where appropriate.
 */
@RestController
@CrossOrigin
@IsOwner
public class DogController {

  // TODO AJOUTER LES JSON VIEWS

  private final DogDao dogDao;
  private final UserDao userDao;

  /**
   * Constructs a new {@code DogController} with the given DAOs.
   *
   * @param dogDao  DAO for performing CRUD operations on Dog entities
   * @param userDao DAO for looking up User entities (owners)
   */
  @Autowired
  public DogController(DogDao dogDao, UserDao userDao) {
    this.dogDao = dogDao;
    this.userDao = userDao;
  }

  /**
   * Retrieves all dogs in the system, regardless of owner.
   * <p>
   * Access restricted to Admin users.
   *
   * @return a {@link ResponseEntity} containing the list of all dogs and HTTP 200 OK
   */
  @IsAdmin // TODO Ici je récupère tous les chiens, peut importe l'utilisateur à qui le chien est lié
  @GetMapping("/dogs")
  public ResponseEntity<List<Dog>> getAllDogs() {
    return new ResponseEntity<>(dogDao.findAll(), HttpStatus.OK);
  }

  /**
   * Retrieves a single dog by its ID.
   * <p>
   * Access restricted to Admin users. Returns 404 if the dog does not exist.
   *
   * @param id the ID of the dog to retrieve
   * @return a {@link ResponseEntity} containing the Dog and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @IsAdmin
  @GetMapping("/dog/{id}")
  public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
    Optional<Dog> optionalDog = dogDao.findById(id);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalDog.get(), HttpStatus.OK);
  }

  /**
   * Creates a new dog for the authenticated owner.
   * <p>
   * The incoming Dog object must be valid. Returns HTTP 201 Created with
   * the saved entity.
   *
   * @param dog the {@link Dog} object to create
   * @return a {@link ResponseEntity} containing the created Dog and HTTP 201 Created
   */
  @PostMapping("/dog")
  public ResponseEntity<Dog> addDog(@RequestBody @Valid Dog dog) {
    dog.setId(null);
    dogDao.save(dog);
    return new ResponseEntity<>(dog, HttpStatus.CREATED);
  }

  /**
   * Updates the authenticated owner’s dog details.
   * <p>
   * Retrieves the current user from the security context, ensures the dog belongs to them,
   * and applies changes to the name, birthday, gender, and breed. Returns:
   * <ul>
   *   <li>401 Unauthorized if the authenticated user cannot be found</li>
   *   <li>404 Not Found if the dog does not exist or is not owned by the user</li>
   *   <li>400 Bad Request if the new breed information is missing or invalid</li>
   *   <li>204 No Content on successful update</li>
   * </ul>
   *
   * @param id  the ID of the dog to update
   * @param dog the {@link Dog} object containing updated name, birthday, gender, and breed
   * @return a {@link ResponseEntity} signaling the result of the operation
   */
  @PutMapping("/dog/{id}")
  public ResponseEntity<Void> updateDogForOneOwner(@PathVariable Long id, @RequestBody @Valid Dog dog) {
    // Get authenticated user's email
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();

    // Verify owner exists
    Optional<User> optionalUser = userDao.findByEmail(email);
    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Long ownerId = optionalUser.get().getId();

    // Verify dog belongs to this owner
    Optional<Dog> optionalDog = dogDao.findByIdAndOwnerId(id, ownerId);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Validate breed information
    if (dog.getBreed() == null || dog.getBreed().getId() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Perform update
    Dog existingDog = optionalDog.get();

    existingDog.setName(dog.getName());
    existingDog.setBirthday(dog.getBirthday());
    existingDog.setGender(dog.getGender());
    existingDog.setBreed(dog.getBreed());

    dogDao.save(existingDog);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
