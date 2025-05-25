package com.k9club.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.BreedDao;
import com.k9club.api.dao.CourseRegistrationDao;
import com.k9club.api.dao.DogDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.dog.DogCreateDto;
import com.k9club.api.dto.dog.DogUpdateDto;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsUser;
import com.k9club.api.model.Breed;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
// @RequestMapping("/api") TODO L'AJOUTER SUR TOUS LES CONTROLLEURS
public class DogController {

  private final DogDao dogDao;
  private final UserDao userDao;
  private final BreedDao breedDao;
  private final CourseRegistrationDao courseRegistrationDao;

  /**
   * Constructs a new {@code DogController} with the required DAOs.
   *
   * @param dogDao                DAO for performing CRUD operations on Dog entities
   * @param userDao               DAO for looking up User entities (owners)
   * @param breedDao              DAO for looking up Breed entities
   * @param courseRegistrationDao DAO for accessing course registrations associated with dogs
   */
  @Autowired
  public DogController(DogDao dogDao, UserDao userDao, BreedDao breedDao, CourseRegistrationDao courseRegistrationDao) {
    this.dogDao = dogDao;
    this.userDao = userDao;
    this.breedDao = breedDao;
    this.courseRegistrationDao = courseRegistrationDao;
  }


  @IsAdmin
  @JsonView(ViewsAdmin.DogsInfo.class)
  @GetMapping("/admin/dogs")
  public ResponseEntity<List<Dog>> getAllDogsForAdmin() {
    return new ResponseEntity<>(dogDao.findAll(), HttpStatus.OK);
  }


  @IsAdmin
  @JsonView(ViewsAdmin.DogsInfo.class)
  @GetMapping("/admin/dog/{id}")
  public ResponseEntity<Dog> getDogByIdForAdmin(@PathVariable Long id) {
    Optional<Dog> optionalDog = dogDao.findById(id);

    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalDog.get(), HttpStatus.OK);
  }

  // TODO VOIR SI CETTE ROUTE EST UTILE ?
//  @IsCoach
//  @JsonView(ViewsCoach.DogsInfo.class)
//  @GetMapping("/coach/dogs")
//  public ResponseEntity<List<Dog>> getAllDogsForCoach() {
//    return new ResponseEntity<>(dogDao.findAll(), HttpStatus.OK);
//  }
//
//
//  @IsCoach
//  @JsonView(ViewsCoach.DogsInfo.class)
//  @GetMapping("/coach/dog/{id}")
//  public ResponseEntity<Dog> getDogByIdForCoach(@PathVariable Long id) {
//    Optional<Dog> optionalDog = dogDao.findById(id);
//
//    if (optionalDog.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    return new ResponseEntity<>(optionalDog.get(), HttpStatus.OK);
//  }

// TODO JE RECUPERE LES CHIENS D'UN OWNER DANS LE OWNERCONTROLLER
//  @JsonView(ViewsOwner.DogsInfo.class)
//  @GetMapping("/dogs")
//  public ResponseEntity<List<Dog>> getAllDogs() {
//    return new ResponseEntity<>(dogDao.findAll(), HttpStatus.OK);
//  }
//
//
//  @JsonView(ViewsOwner.DogsInfo.class)
//  @GetMapping("/dog/{id}")
//  public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
//    Optional<Dog> optionalDog = dogDao.findById(id);
//
//    if (optionalDog.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    return new ResponseEntity<>(optionalDog.get(), HttpStatus.OK);
//  }

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
  @JsonView(ViewsUser.Owner.class)
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


  /**
   * Retrieves all course registrations for a specific dog owned by the authenticated user.
   * <p>
   * Validates that the dog exists and belongs to the current owner. Returns:
   * <ul>
   *   <li>404 Not Found if the dog does not exist</li>
   *   <li>403 Forbidden if the dog exists but is not owned by the authenticated user</li>
   *   <li>200 OK with the list of {@link CourseRegistration} entities otherwise</li>
   * </ul>
   *
   * @param appUserDetails the security principal containing the authenticated user
   * @param id             the ID of the dog whose registrations are requested
   * @return a {@link ResponseEntity} with the list of registrations and HTTP 200 OK,
   * or the appropriate HTTP error status
   */
  @JsonView(ViewsUser.Owner.class)
  @GetMapping("/dog/{id}/course-registrations")
  public ResponseEntity<List<CourseRegistration>> getAllCourseRegistrationsForOneDog(
      @AuthenticationPrincipal AppUserDetails appUserDetails,
      @PathVariable Long id
  ) {
    Optional<Dog> optionalDog = dogDao.findById(id);
    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Dog existingDog = optionalDog.get();

    if (!existingDog.getOwner().getId().equals(appUserDetails.getUser().getId())) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    List<CourseRegistration> courseRegistrations = existingDog.getRegistrations();

    return new ResponseEntity<>(courseRegistrations, HttpStatus.OK);
  }

  /**
   * Retrieves a specific course registration for a given dog owned by the authenticated user.
   * <p>
   * Validates that the dog exists, belongs to the current owner, and that the registration
   * exists for that dog. Returns:
   * <ul>
   *   <li>404 Not Found if the dog or the registration does not exist</li>
   *   <li>403 Forbidden if the dog exists but is not owned by the authenticated user</li>
   *   <li>200 OK with the {@link CourseRegistration} entity otherwise</li>
   * </ul>
   *
   * @param appUserDetails the security principal containing the authenticated user
   * @param dogId          the ID of the dog
   * @param registrationId the ID of the course registration to retrieve
   * @return a {@link ResponseEntity} with the registration and HTTP 200 OK,
   * or the appropriate HTTP error status
   */
  @JsonView(ViewsUser.Owner.class)
  @GetMapping("/dog/{dogId}/course-registration/{registrationId}")
  public ResponseEntity<CourseRegistration> getDogCourseRegistration(
      @AuthenticationPrincipal AppUserDetails appUserDetails,
      @PathVariable Long dogId,
      @PathVariable Long registrationId
  ) {

    Optional<Dog> optionalDog = dogDao.findById(dogId);
    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Dog dog = optionalDog.get();

    // Vérifier que c’est bien le chien de l'utilisateur connecté
    if (!dog.getOwner().getId().equals(appUserDetails.getUser().getId())) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    // Récupération de la registration (id + dogId)
    Optional<CourseRegistration> optionalRegistration =
        courseRegistrationDao.findByIdAndDogId(registrationId, dogId);

    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    CourseRegistration registration = optionalRegistration.get();

    return new ResponseEntity<>(registration, HttpStatus.OK);
  }
}
