package com.k9club.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.CourseTypeDao;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsCoach;
import com.k9club.api.jsonview.ViewsOwner;
import com.k9club.api.model.CourseType;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsCoach;
import com.k9club.api.security.annotations.IsOwner;
import jakarta.validation.Valid;
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

  private final CourseTypeDao courseTypeDao;

  /**
   * Constructs a new CourseTypeController with the given DAO.
   *
   * @param courseTypeDao the DAO used to perform CRUD operations on CourseType entities
   */
  @Autowired
  public CourseTypeController(CourseTypeDao courseTypeDao) {
    this.courseTypeDao = courseTypeDao;
  }

  @IsAdmin
  @JsonView(ViewsAdmin.CourseTypeInfo.class)
  @GetMapping("/admin/course-types")
  public ResponseEntity<List<CourseType>> getAllTypesForAdmin() {
    return new ResponseEntity<>(courseTypeDao.findAll(), HttpStatus.OK);
  }

  @IsAdmin
  @JsonView(ViewsAdmin.CourseTypeInfo.class)
  @GetMapping("/admin/course-type/{id}")
  public ResponseEntity<CourseType> getTypeByIdForAdmin(@PathVariable Long id) {
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);
    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourseType.get(), HttpStatus.OK);
  }

  // TODO POUR LES COACHS TRIER POUR AVOIR LES COURS QUI LUI SONT CONCERNES AVEC AUTHENTICATION PROVIDER
  // TODO CAR DANS LA REPONSE JSON JE NE RENVOIS PAS LE COACH QUI EST ASSOCIE A UN COUS
  // TODO C'EST LES SIENS FORCEMENT

  @IsCoach
  @JsonView(ViewsCoach.CourseTypeInfo.class)
  @GetMapping("/coach/course-types")
  public ResponseEntity<List<CourseType>> getAllTypesForCoach() {
    return new ResponseEntity<>(courseTypeDao.findAll(), HttpStatus.OK);
  }

  @IsCoach
  @JsonView(ViewsCoach.CourseTypeInfo.class)
  @GetMapping("/coach/course-type/{id}")
  public ResponseEntity<CourseType> getTypeByIdForCoach(@PathVariable Long id) {
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);
    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourseType.get(), HttpStatus.OK);
  }


  @JsonView(ViewsOwner.CourseTypeInfo.class)
  @GetMapping("/course-types")
  public ResponseEntity<List<CourseType>> getAllTypes() {
    return new ResponseEntity<>(courseTypeDao.findAll(), HttpStatus.OK);
  }

  @JsonView(ViewsOwner.CourseTypeInfo.class)
  @GetMapping("/course-type/{id}")
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
  @PostMapping("/course-type")
  public ResponseEntity<CourseType> addCourseType(@RequestBody @Valid CourseType courseType) {
    courseType.setId(null);
    courseTypeDao.save(courseType);
    return new ResponseEntity<>(courseType, HttpStatus.CREATED);
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
  @PutMapping("/course-type/{id}")
  public ResponseEntity<Void> updateCourseType(@PathVariable Long id, @RequestBody @Valid CourseType courseType) {
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(id);

    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    CourseType existingCourseType = optionalCourseType.get();

    existingCourseType.setName(courseType.getName());
    existingCourseType.setTextColor(courseType.getTextColor());
    existingCourseType.setBackgroundColor(courseType.getBackgroundColor());

    courseTypeDao.save(existingCourseType);

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
  @DeleteMapping("/course-type/{id}")
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
