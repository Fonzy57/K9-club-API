package com.k9club.api.controller;

import com.k9club.api.dao.CourseDao;
import com.k9club.api.model.Course;
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
 * Controller responsible for managing courses.
 * <p>
 * Owners can list and view courses; Admins can create, update, and delete them.
 */
@RestController
@CrossOrigin
@IsOwner
public class CourseController {

  // TODO AJOUTER LES JSON VIEWS

  protected CourseDao courseDao;

  /**
   * Constructor injecting the CourseDao dependency.
   *
   * @param courseDao the DAO used to perform CRUD operations on Course entities
   */
  @Autowired
  public CourseController(CourseDao courseDao) {
    this.courseDao = courseDao;
  }

  /**
   * Retrieves a list of all courses.
   *
   * @return a ResponseEntity containing the list of courses and HTTP 200 OK
   */
  @GetMapping("/courses")
  public ResponseEntity<List<Course>> getAllCourses() {
    return new ResponseEntity<>(courseDao.findAll(), HttpStatus.OK);
  }

  /**
   * Retrieves a specific course by its ID.
   * Returns HTTP 404 if no course exists with the given ID.
   *
   * @param id the ID of the course to retrieve
   * @return a ResponseEntity containing the Course and HTTP 200 OK,
   * or HTTP 404 Not Found if not found
   */
  @GetMapping("/course/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    Optional<Course> optionalCourse = courseDao.findById(id);
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourse.get(), HttpStatus.OK);
  }

  /**
   * Creates a new course.
   * Only accessible by ADMIN users.
   *
   * @param course the Course object to create
   * @return a ResponseEntity containing the created Course and HTTP 200 OK
   */
  // TODO VOIR SI JE LAISSE LES COACHS CREER LEUR COURS OU PAS
  @IsAdmin
  @PostMapping("/course")
  public ResponseEntity<Course> addCourse(@RequestBody @Valid Course course) {
    course.setId(null);
    courseDao.save(course);
    return new ResponseEntity<>(course, HttpStatus.OK);
  }

  /**
   * Updates an existing course by its ID.
   * Only accessible by ADMIN users.
   * Returns HTTP 404 if no course exists with the given ID.
   *
   * @param id     the ID of the Course to update
   * @param course the Course object containing updated fields
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the Course does not exist
   */
  @IsAdmin
  @PutMapping("/course/{id}")
  public ResponseEntity<Void> updateCourse(@PathVariable Long id, @RequestBody @Valid Course course) {
    Optional<Course> optionalCourse = courseDao.findById(id);
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Course existingCourse = optionalCourse.get();

    existingCourse.setName(course.getName());
    existingCourse.setDescription(course.getDescription());
    existingCourse.setMaxParticipants(course.getMaxParticipants());
    existingCourse.setStartDate(course.getStartDate());
    existingCourse.setEndDate(course.getEndDate());
    existingCourse.setCoach(course.getCoach());
    existingCourse.setCourseType(course.getCourseType());
    existingCourse.setAgeRange(course.getAgeRange());

    courseDao.save(existingCourse);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Deletes a course by its ID.
   * Only accessible by ADMIN users.
   * Returns HTTP 404 if no course exists with the given ID.
   * <p>
   * Consider anonymizing related data instead of performing a hard delete.
   *
   * @param id the ID of the Course to delete
   * @return a ResponseEntity with HTTP 204 No Content on success,
   * or HTTP 404 Not Found if the Course does not exist
   */
  @IsAdmin
  @DeleteMapping("/course/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    Optional<Course> optionalCourse = courseDao.findById(id);

    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //


    // TODO A LA PLACE DE DELETE VOIR POUR ANONYMISER LES DONNEES


    //

    courseDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
