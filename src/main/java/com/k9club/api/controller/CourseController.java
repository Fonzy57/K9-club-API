package com.k9club.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.dao.AgeRangeDao;
import com.k9club.api.dao.CourseDao;
import com.k9club.api.dao.CourseTypeDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.course.CourseCreateDto;
import com.k9club.api.dto.course.CourseUpdateDto;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsCoach;
import com.k9club.api.jsonview.ViewsOwner;
import com.k9club.api.model.AgeRange;
import com.k9club.api.model.Course;
import com.k9club.api.model.CourseType;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.security.annotations.IsAdmin;
import com.k9club.api.security.annotations.IsCoach;
import com.k9club.api.security.annotations.IsOwner;
import com.k9club.api.service.CourseService;
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

  private final CourseDao courseDao;
  private final CourseTypeDao courseTypeDao;
  private final AgeRangeDao ageRangeDao;
  private final UserDao userDao;
  private final CourseService courseService;

  /**
   * Constructs a new {@code CourseController} and wires required dependencies.
   *
   * @param courseDao     DAO for {@link Course} entities
   * @param courseTypeDao DAO for {@link CourseType} entities
   * @param ageRangeDao   DAO for {@link AgeRange} entities
   * @param userDao       DAO for {@link User} entities (e.g., coach lookups)
   * @param courseService service handling business logic for courses (creation, validations, etc.)
   */

  @Autowired
  public CourseController(CourseDao courseDao, CourseTypeDao courseTypeDao, AgeRangeDao ageRangeDao, UserDao userDao, CourseService courseService) {
    this.courseDao = courseDao;
    this.courseTypeDao = courseTypeDao;
    this.ageRangeDao = ageRangeDao;
    this.userDao = userDao;
    this.courseService = courseService;
  }

  @IsAdmin
  @JsonView(ViewsAdmin.CourseInfo.class)
  @GetMapping("/admin/courses")
  public ResponseEntity<List<Course>> getAllCoursesForAdmin() {
    return new ResponseEntity<>(courseDao.findAll(), HttpStatus.OK);
  }


  @IsAdmin
  @JsonView(ViewsAdmin.CourseInfo.class)
  @GetMapping("/admin/course/{id}")
  public ResponseEntity<Course> getCourseByIdForAdmin(@PathVariable Long id) {
    Optional<Course> optionalCourse = courseDao.findById(id);
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourse.get(), HttpStatus.OK);
  }

  @IsCoach
  @JsonView(ViewsCoach.CourseInfo.class)
  @GetMapping("/coach/courses")
  public ResponseEntity<List<Course>> getAllCoursesForCoach() {
    return new ResponseEntity<>(courseDao.findAll(), HttpStatus.OK);
  }


  @IsCoach
  @JsonView(ViewsCoach.CourseInfo.class)
  @GetMapping("/coach/course/{id}")
  public ResponseEntity<Course> getCourseByIdForCoach(@PathVariable Long id) {
    Optional<Course> optionalCourse = courseDao.findById(id);
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourse.get(), HttpStatus.OK);
  }

  @JsonView(ViewsOwner.CourseInfo.class)
  @GetMapping("/courses")
  public ResponseEntity<List<Course>> getAllCourses() {
    return new ResponseEntity<>(courseDao.findAll(), HttpStatus.OK);
  }

  @JsonView(ViewsOwner.CourseInfo.class)
  @GetMapping("/course/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    Optional<Course> optionalCourse = courseDao.findById(id);
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(optionalCourse.get(), HttpStatus.OK);
  }

  /**
   * Creates a new {@link Course} from the given {@link CourseCreateDto}.
   * <p>
   * The request body is validated ({@code @Valid}). Business validation and lookups
   * are delegated to {@link com.k9club.api.service.CourseService#addCourse(CourseCreateDto)}.
   * On success, returns HTTP 201 with the created entity serialized with
   * <p>Access: ADMIN only.</p>
   *
   * @param courseCreateDto DTO with the data to create a course
   * @return 201 (Created) with the persisted {@link Course}
   * @throws org.springframework.web.server.ResponseStatusException if a validation fails (e.g. 400/404)
   */
  @IsAdmin
  @JsonView(ViewsAdmin.CourseInfo.class)
  @PostMapping("/course")
  public ResponseEntity<Course> addCourse(@RequestBody @Valid CourseCreateDto courseCreateDto) {
    Course createdCourse = courseService.addCourse(courseCreateDto);
    return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
  }


  // TODO FAIRE UNE METHODE /course/{id}/cancel POUR ANNULER UN COURS

  /**
   * Updates an existing course using the provided data transfer object.
   * <p>
   * Validates that the course exists, and that the referenced coach, course type,
   * and age range all exist and are valid. If any are missing, returns HTTP 404.
   * Otherwise updates the course fields and associations, persists the changes,
   * and returns HTTP 204 No Content.
   *
   * @param id              the ID of the course to update
   * @param courseUpdateDto the DTO containing updated course data
   * @return a {@link ResponseEntity} with HTTP 204 on success,
   * or HTTP 404 if the course or any linked entity cannot be found
   */
  @IsAdmin
  @PutMapping("/course/{id}")
  public ResponseEntity<Void> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseUpdateDto courseUpdateDto) {
    // Check if the course to update exists
    Optional<Course> optionalCourse = courseDao.findById(id);
    if (optionalCourse.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO REFACTORISER CETTE PARTIE CAR IDENTIQUE DANS POST
    // Check if the coach exists and is a COACH
    Optional<User> optionalCoach = userDao.findByIdAndUserRole(courseUpdateDto.getCoachId(), UserRole.COACH);
    if (optionalCoach.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Check if the course type exists
    Optional<CourseType> optionalCourseType = courseTypeDao.findById(courseUpdateDto.getCourseTypeId());
    if (optionalCourseType.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Check if the age range exists
    Optional<AgeRange> optionalAgeRange = ageRangeDao.findById(courseUpdateDto.getAgeRangeId());
    if (optionalAgeRange.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update the existing course entity
    Course course = optionalCourse.get();

    course.setName(courseUpdateDto.getName());
    course.setDescription(courseUpdateDto.getDescription());
    course.setMaxParticipants(courseUpdateDto.getMaxParticipants());
    course.setStartDate(courseUpdateDto.getStartDate());
    course.setEndDate(courseUpdateDto.getEndDate());

    course.setCoach(optionalCoach.get());
    course.setCourseType(optionalCourseType.get());
    course.setAgeRange(optionalAgeRange.get());

    courseDao.save(course);

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

    // TODO ICI SI SUPPRIMER EN FAIT ON ANNULE LE COURS, CAR SI DEJA DES RESERVATIONS IL FAUT L'AFFICHER AUX
    //  UTILISATEURS


    //

    courseDao.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
