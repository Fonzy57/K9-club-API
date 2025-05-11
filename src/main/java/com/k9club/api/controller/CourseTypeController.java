package com.k9club.api.controller;

import com.k9club.api.dao.CourseTypeDao;
import com.k9club.api.model.CourseType;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for managing course types.
 * <p>
 * Provides endpoints for owners to view all course types and retrieve a single type by ID,
 * and for admins to create, update, and delete course types.
 * Access is restricted via {@code @IsOwner} and {@code @IsAdmin} annotations.
 */
@RestController
@CrossOrigin
@IsOwner
public class CourseTypeController {

  // TODO AJOUTER LES JSON VIEWS

  protected CourseTypeDao courseTypeDao;

  /**
   * Constructs a new CourseTypeController with the given DAO.
   *
   * @param courseTypeDao the DAO used to perform CRUD operations on CourseType entities
   */
  @Autowired
  public CourseTypeController(CourseTypeDao courseTypeDao) {
    this.courseTypeDao = courseTypeDao;
  }

  /**
   * Retrieves all available course types.
   *
   * @return a ResponseEntity containing the list of all CourseType entities and HTTP 200 OK
   */
  @GetMapping("/types")
  public ResponseEntity<List<CourseType>> getAllTypes() {
    return new ResponseEntity<>(courseTypeDao.findAll(), HttpStatus.OK);
  }

  /**
   * Retrieves a course type by its unique identifier.
   * Returns HTTP 404 if no type exists with the given ID.
   *
   * @param id the ID of the CourseType to retrieve
   * @return a ResponseEntity containing the CourseType and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @GetMapping("/type/{id}")
  public ResponseEntity<CourseType> getTypeById(@PathVariable Long id) {
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);
    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourseType.get(), HttpStatus.OK);
  }

  /**
   * Creates a new course type.
   * Only accessible by ADMIN users.
   *
   * @param courseType the CourseType object to create
   * @return a ResponseEntity containing the created CourseType and HTTP 200 OK
   */
  @IsAdmin
  @PostMapping("/type")
  public ResponseEntity<CourseType> addCourseType(@RequestBody CourseType courseType) {
    courseType.setId(null);
    courseTypeDao.save(courseType);
    return new ResponseEntity<>(courseType, HttpStatus.OK);
  }

  /**
   * Updates an existing course type by ID.
   * Only accessible by ADMIN users.
   * Returns HTTP 404 if no type exists with the given ID.
   *
   * @param id         the ID of the CourseType to update
   * @param courseType the CourseType object containing updated values
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the CourseType does not exist
   */
  @IsAdmin
  @PutMapping("/type/{id}")
  public ResponseEntity<CourseType> updateCourseType(@PathVariable Long id, @RequestBody CourseType courseType) {
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);
    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    CourseType course = optionalCourseType.get();

    course.setName(courseType.getName());
    course.setTextColor(courseType.getTextColor());
    course.setBackgroundColor(courseType.getBackgroundColor());

    courseTypeDao.save(courseType);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a course type by ID.
   * Only accessible by ADMIN users.
   * Returns HTTP 404 if no type exists with the given ID.
   * <p>
   * Consider anonymizing related data instead of performing a hard delete.
   *
   * @param id the ID of the CourseType to delete
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the CourseType does not exist
   */
  @IsAdmin
  @DeleteMapping("/type/{id}")
  public ResponseEntity<CourseType> deleteCourseType(@PathVariable Long id) {
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);

    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    courseTypeDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
