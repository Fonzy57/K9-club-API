package com.k9club.api.controller;

import com.k9club.api.dao.BreedDao;
import com.k9club.api.dao.DogDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.dog.DogCreateDto;
import com.k9club.api.dto.dog.DogUpdateDto;
import com.k9club.api.model.Breed;
import com.k9club.api.model.Dog;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
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
 * - Owners can create, update, and delete their own dogs.
 * - Admins can list and view any dog in the system.
 * <p>
 * Security annotations:
 * {@code @IsOwner} restricts modifications to owner-specific endpoints,
 * {@code @IsAdmin} allows administrative access where required.
 */
@RestController
@CrossOrigin
@IsOwner
public class DogController {

  // TODO AJOUTER LES JSON VIEWS

  // TODO FAIRE LES ROUTES POUR RÉCUPÉRER LES REGISTRATIONS D'UN CHIEN AVEC SON ID

  // TODO PAR EXEMPLE ROUTE : /dog/{id}/course-registrations
  // TODO PAR EXEMPLE ROUTE : /dog/{id}/course-registration/{id} POUR UN COURS PRECIS

  private final DogDao dogDao;
  private final UserDao userDao;
  private final BreedDao breedDao;

  /**
   * Constructs a new {@code DogController} with the required DAOs.
   *
   * @param dogDao   DAO for performing CRUD operations on Dog entities
   * @param userDao  DAO for looking up User entities (owners)
   * @param breedDao DAO for looking up Breed entities
   */
  @Autowired
  public DogController(DogDao dogDao, UserDao userDao, BreedDao breedDao) {
    this.dogDao = dogDao;
    this.userDao = userDao;
    this.breedDao = breedDao;
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
   * Access restricted to Admin users. Returns HTTP 404 if no dog exists with the given ID.
   *
   * @param id the ID of the dog to retrieve
   * @return a {@link ResponseEntity} containing the Dog and HTTP 200 OK,
   * or HTTP 404 Not Found if the dog is not found
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
   * Creates a new dog for the specified owner using the provided DTO.
   * <p>
   * Validates that the owner exists and has the OWNER role, and that the specified breed exists.
   * If either check fails, returns HTTP 400 Bad Request. Otherwise constructs a new {@link Dog}
   * entity, populates its fields, persists it, and returns HTTP 201 Created with the saved entity.
   *
   * @param dogCreateDto the DTO containing name, birthdate, gender, ownerId, and breedId for the new dog
   * @return a {@link ResponseEntity} containing the created {@link Dog} and HTTP 201 Created,
   * or HTTP 400 Bad Request if the owner or breed cannot be found or is invalid
   */
  @PostMapping("/dog")
  public ResponseEntity<Dog> addDog(@RequestBody @Valid DogCreateDto dogCreateDto) {
    Optional<User> optionalUser = userDao.findByIdAndUserRole(dogCreateDto.getOwnerId(), UserRole.OWNER);

    if (optionalUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Optional<Breed> optionalBreed = breedDao.findById(dogCreateDto.getBreedId());

    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Dog dog = new Dog();
    dog.setName(dogCreateDto.getName());
    dog.setBirthdate(dogCreateDto.getBirthdate());
    dog.setGender(dogCreateDto.getGender());
    dog.setOwner(optionalUser.get());
    dog.setBreed(optionalBreed.get());

    dog.setId(null);

    dogDao.save(dog);

    return new ResponseEntity<>(dog, HttpStatus.CREATED);
  }

  /**
   * Updates the authenticated owner’s dog details.
   * <p>
   * Retrieves the current user, ensures the dog belongs to them, and that the new breed exists.
   * Applies updates to name, birthdate, gender, and breed. Returns:
   * <ul>
   *   <li>401 Unauthorized if the user is not found</li>
   *   <li>404 Not Found if the dog does not exist or is not owned by the user</li>
   *   <li>400 Bad Request if the specified breed is invalid</li>
   *   <li>204 No Content on successful update</li>
   * </ul>
   *
   * @param id           the ID of the dog to update
   * @param dogUpdateDto the DTO containing updated name, birthdate, gender, and breedId
   * @return a {@link ResponseEntity} with the appropriate HTTP status
   */
  @PutMapping("/dog/{id}")
  public ResponseEntity<Void> updateDogForOneOwner(@PathVariable Long id, @RequestBody @Valid DogUpdateDto dogUpdateDto) {
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
    Optional<Breed> optionalBreed = breedDao.findById(dogUpdateDto.getBreedId());
    if (optionalBreed.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Perform update
    Dog existingDog = optionalDog.get();

    existingDog.setName(dogUpdateDto.getName());
    existingDog.setBirthdate(dogUpdateDto.getBirthdate());
    existingDog.setGender(dogUpdateDto.getGender());
    existingDog.setBreed(optionalBreed.get());

    dogDao.save(existingDog);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a dog by its ID.
   * <p>
   * Owners may delete their own dogs; Admins can delete any dog (method shares ownership check).
   * Returns HTTP 404 if the dog does not exist, otherwise deletes and returns HTTP 204 No Content.
   *
   * @param id the ID of the dog to delete
   * @return a {@link ResponseEntity} with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the dog does not exist
   */
  @DeleteMapping("/dog/{id}")
  public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
    Optional<Dog> optionalDog = dogDao.findById(id);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    dogDao.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
