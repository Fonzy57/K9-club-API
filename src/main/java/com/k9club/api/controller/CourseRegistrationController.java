package com.k9club.api.controller;

import com.k9club.api.dao.CourseRegistrationDao;
import com.k9club.api.model.Registration;
import com.k9club.api.security.annotations.IsOwner;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing course registrations.
 * <p>
 * Provides endpoints to list, retrieve, create, update, and delete course registrations.
 * Access is restricted to owner users via the {@code @IsOwner} annotation.
 */
@RestController
@CrossOrigin
@IsOwner
public class CourseRegistrationController {


  protected CourseRegistrationDao courseRegistrationDao;

  /**
   * Constructor injecting the CourseRegistrationDao dependency.
   *
   * @param courseRegistrationDao the DAO used to perform CRUD operations on Registration entities
   */
  @Autowired
  public CourseRegistrationController(CourseRegistrationDao courseRegistrationDao) {
    this.courseRegistrationDao = courseRegistrationDao;
  }

  /**
   * Retrieves a list of all course registrations.
   *
   * @return a ResponseEntity containing the list of registrations and HTTP 200 OK
   */
  @GetMapping("/course-registrations")
  public ResponseEntity<List<Registration>> getAllCourseRegistrations() {
    return new ResponseEntity<>(courseRegistrationDao.findAll(), HttpStatus.OK);
  }

  /**
   * Retrieves a specific course registration by its ID.
   * Returns HTTP 404 if no registration exists with the given ID.
   *
   * @param id the ID of the registration to retrieve
   * @return a ResponseEntity containing the registration and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @GetMapping("/course-registration/{id}")
  public ResponseEntity<Registration> getCourseRegistrationById(@PathVariable Long id) {
    Optional<Registration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalRegistration.get(), HttpStatus.OK);
  }

  /**
   * Creates a new course registration.
   *
   * @param registration the Registration object to create
   * @return a ResponseEntity containing the created registration and HTTP 201 Created
   */
  @PostMapping("/course-registration")
  public ResponseEntity<Registration> addCourseRegistration(@RequestBody @Valid Registration registration) {
    registration.setId(null);
    courseRegistrationDao.save(registration);

    return new ResponseEntity<>(registration, HttpStatus.CREATED);
  }

  /**
   * Updates an existing course registration by ID.
   * Returns HTTP 404 if no registration exists with the given ID.
   *
   * @param id           the ID of the registration to update
   * @param registration the Registration object containing updated fields
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the registration does not exist
   */
  @PutMapping("/course-registration/{id}")
  public ResponseEntity<Void> updateCourseRegistration(@PathVariable Long id,
      @RequestBody @Valid Registration registration) {
    Optional<Registration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Registration existingRegistration = optionalRegistration.get();

    existingRegistration.setRegistrationDate(registration.getRegistrationDate());
    existingRegistration.setStatus(registration.getStatus());
    existingRegistration.setCourse(registration.getCourse());
    existingRegistration.setDog(registration.getDog());

    courseRegistrationDao.save(existingRegistration);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a course registration by its ID.
   * Returns HTTP 404 if no registration exists with the given ID.
   * <p>
   * Consider anonymizing related data instead of performing a hard delete.
   *
   * @param id the ID of the registration to delete
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the registration does not exist
   */
  @DeleteMapping("/course-registration/{id}")
  public ResponseEntity<Void> deleteCourseRegistration(@PathVariable Long id) {
    Optional<Registration> optionalRegistration = courseRegistrationDao.findById(id);
    if (optionalRegistration.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    courseRegistrationDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
