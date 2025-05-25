package com.k9club.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.CourseDao;
import com.k9club.api.dao.CourseRegistrationDao;
import com.k9club.api.dao.DogDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.courseRegistration.CourseRegistrationCreateDto;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsCoach;
import com.k9club.api.jsonview.ViewsOwner;
import com.k9club.api.model.Course;
import com.k9club.api.model.CourseRegistration;
import com.k9club.api.model.Dog;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.CourseRegistrationStatus;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsCoach;
import com.k9club.api.security.annotations.IsOwner;
import com.k9club.api.security.annotations.IsSuperAdmin;
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
 * REST controller for managing course registrations.
 * <p>
 * Owners can create and cancel registrations for their own dogs.
 * Admins can list and view any registration.
 * Super-admins can delete registrations.
 * <p>
 * Security annotations:
 * - {@code @IsOwner} applies to all endpoints by default.
 * - {@code @IsAdmin} and {@code @IsSuperAdmin} override for specific methods.
 */
@RestController
@CrossOrigin
@IsOwner
public class CourseRegistrationController {

  // TODO AJOUTER LES JSON VIEWS

  private final CourseRegistrationDao courseRegistrationDao;
  private final DogDao dogDao;
  private final CourseDao courseDao;
  private final UserDao userDao;

  /**
   * Constructs a new CourseRegistrationController with the required DAOs.
   *
   * @param courseRegistrationDao DAO for performing CRUD on CourseRegistration entities
   * @param dogDao                DAO for performing CRUD on Dog entities
   * @param courseDao             DAO for performing CRUD on Course entities
   * @param userDao               DAO for performing CRUD on User entities
   */
  @Autowired
  public CourseRegistrationController(CourseRegistrationDao courseRegistrationDao, DogDao dogDao, CourseDao courseDao, UserDao userDao) {
    this.courseRegistrationDao = courseRegistrationDao;
    this.dogDao = dogDao;
    this.courseDao = courseDao;
    this.userDao = userDao;
  }


  @IsAdmin
  @JsonView(ViewsAdmin.CourseRegistrationInfo.class)
  @GetMapping("/admin/course-registrations")
  public ResponseEntity<List<CourseRegistration>> getAllCourseRegistrationsForAdmin() {
    return new ResponseEntity<>(courseRegistrationDao.findAll(), HttpStatus.OK);
  }


  @IsAdmin
  @JsonView(ViewsAdmin.CourseRegistrationInfo.class)
  @GetMapping("/admin/course-registration/{id}")
  public ResponseEntity<CourseRegistration> getCourseRegistrationByIdForAdmin(@PathVariable Long id) {
    Optional<CourseRegistration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalRegistration.get(), HttpStatus.OK);
  }

  @IsCoach
  @JsonView(ViewsCoach.CourseRegistrationInfo.class)
  @GetMapping("/coach/course-registrations")
  public ResponseEntity<List<CourseRegistration>> getAllCourseRegistrationsForCoach() {
    return new ResponseEntity<>(courseRegistrationDao.findAll(), HttpStatus.OK);
  }


  @IsCoach
  @JsonView(ViewsCoach.CourseRegistrationInfo.class)
  @GetMapping("/coach/course-registration/{id}")
  public ResponseEntity<CourseRegistration> getCourseRegistrationByIdForCoach(@PathVariable Long id) {
    Optional<CourseRegistration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalRegistration.get(), HttpStatus.OK);
  }

  @JsonView(ViewsOwner.CourseRegistrationInfo.class)
  @GetMapping("/course-registrations")
  public ResponseEntity<List<CourseRegistration>> getAllCourseRegistrations() {
    return new ResponseEntity<>(courseRegistrationDao.findAll(), HttpStatus.OK);
  }

  @JsonView(ViewsOwner.CourseRegistrationInfo.class)
  @GetMapping("/course-registration/{id}")
  public ResponseEntity<CourseRegistration> getCourseRegistrationById(@PathVariable Long id) {
    Optional<CourseRegistration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalRegistration.get(), HttpStatus.OK);
  }

  /**
   * Creates a new course registration.
   * <p>
   * Validates that both the dog and course exist.
   * Returns HTTP 404 if either resource is not found.
   * Otherwise, persists a new CourseRegistration with the given status and returns HTTP 201 Created.
   *
   * @param courseRegistrationCreateDto the DTO containing dogId, courseId, and initial status
   * @return a ResponseEntity containing the created CourseRegistration and HTTP 201 Created,
   * or HTTP 404 Not Found if the dog or course is missing
   */
  @PostMapping("/course-registration")
  public ResponseEntity<CourseRegistration> addCourseRegistration(@RequestBody @Valid CourseRegistrationCreateDto courseRegistrationCreateDto) {

    // Check if dog exists
    Optional<Dog> optionalDog = dogDao.findById(courseRegistrationCreateDto.getDogId());
    if (optionalDog.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Check if course exists
    Optional<Course> optionalCourse = courseDao.findById(courseRegistrationCreateDto.getCourseId());
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    CourseRegistration courseRegistration = new CourseRegistration();
    courseRegistration.setStatus(courseRegistrationCreateDto.getStatus());
    courseRegistration.setDog(optionalDog.get());
    courseRegistration.setCourse(optionalCourse.get());

    courseRegistrationDao.save(courseRegistration);

    return new ResponseEntity<>(courseRegistration, HttpStatus.CREATED);
  }

  /**
   * Cancels an existing course registration.
   * <p>
   * Retrieves the authenticated user, verifies ownership of the dog in the registration,
   * and sets the registration status to CANCELLED.
   * Returns:
   * <ul>
   *   <li>401 Unauthorized if the user cannot be found</li>
   *   <li>404 Not Found if the registration does not exist</li>
   *   <li>403 Forbidden if the user does not own the dog</li>
   *   <li>204 No Content on successful cancellation</li>
   * </ul>
   *
   * @param id the ID of the registration to cancel
   * @return a ResponseEntity signaling the result of the operation
   */
  @PatchMapping("/course-registration/{id}/cancel")
  public ResponseEntity<Void> cancelCourseRegistration(@PathVariable Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();

    // 1. Récupérer l'utilisateur connecté
    Optional<User> optionaluser = userDao.findByEmail(email);
    if (optionaluser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    User currentUser = optionaluser.get();

    // 2. Récupérer la réservation
    Optional<CourseRegistration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    CourseRegistration registration = optionalRegistration.get();

    // 3. Vérifier que l'utilisateur est bien propriétaire du chien lié à la réservation
    User ownerOfDog = registration.getDog().getOwner();

    if (!ownerOfDog.getId().equals(currentUser.getId())) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    // 4. Annuler la réservation
    registration.setStatus(CourseRegistrationStatus.CANCELLED);

    courseRegistrationDao.save(registration);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a course registration by its ID.
   * <p>
   * Access restricted to Super-admin users.
   * Returns HTTP 404 if no registration exists with the given ID,
   * otherwise performs a hard delete and returns HTTP 204 No Content.
   *
   * @param id the ID of the registration to delete
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the registration does not exist
   */
  @IsSuperAdmin
  @DeleteMapping("/course-registration/{id}")
  public ResponseEntity<Void> deleteCourseRegistration(@PathVariable Long id) {
    Optional<CourseRegistration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    courseRegistrationDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
