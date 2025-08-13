package com.k9club.api.service.impl;

import com.k9club.api.dao.AgeRangeDao;
import com.k9club.api.dao.CourseDao;
import com.k9club.api.dao.CourseTypeDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.course.CourseCreateDto;
import com.k9club.api.model.AgeRange;
import com.k9club.api.model.Course;
import com.k9club.api.model.CourseType;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of the {@link CourseService} interface.
 * <p>
 * Handles the business logic for creating courses, including validation
 * of related entities and ensuring consistency before persisting to the database.
 * <p>
 * Annotated with {@link org.springframework.stereotype.Service} to indicate a service layer component
 * and {@link org.springframework.transaction.annotation.Transactional} to ensure that
 * all database operations within a method occur within a single transaction.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

  private final CourseDao courseDao;
  private final CourseTypeDao courseTypeDao;
  private final AgeRangeDao ageRangeDao;
  private final UserDao userDao;

  /**
   * Constructs a new {@link CourseServiceImpl} with required dependencies.
   *
   * @param courseDao     DAO for managing {@link Course} entities
   * @param courseTypeDao DAO for managing {@link CourseType} entities
   * @param ageRangeDao   DAO for managing {@link AgeRange} entities
   * @param userDao       DAO for managing {@link User} entities
   */
  public CourseServiceImpl(CourseDao courseDao, CourseTypeDao courseTypeDao, AgeRangeDao ageRangeDao, UserDao userDao) {
    this.courseDao = courseDao;
    this.courseTypeDao = courseTypeDao;
    this.ageRangeDao = ageRangeDao;
    this.userDao = userDao;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation performs the following steps:
   * <ol>
   *   <li>Validates that startDate is before endDate; otherwise throws HTTP 400</li>
   *   <li>Retrieves and validates that the coach exists and has the COACH role; otherwise throws HTTP 404</li>
   *   <li>Retrieves and validates the course type; otherwise throws HTTP 404</li>
   *   <li>Retrieves and validates the age range; otherwise throws HTTP 404</li>
   *   <li>Constructs and persists a new {@link Course} entity with the provided data</li>
   * </ol>
   *
   * @param dto DTO containing all required data to create a course
   * @return the persisted {@link Course} entity
   * @throws org.springframework.web.server.ResponseStatusException if any validation fails
   */
  @Override
  public Course addCourse(CourseCreateDto dto) {
    if (dto.getStartDate().compareTo(dto.getEndDate()) >= 0) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "La date de début ne peut pas être après la date de fin."
      );
    }

    User coach = userDao.findByIdAndUserRole(dto.getCoachId(), UserRole.COACH)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coach introuvable."));

    CourseType courseType = courseTypeDao.findById(dto.getCourseTypeId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type de cours introuvable."));

    AgeRange ageRange = ageRangeDao.findById(dto.getAgeRangeId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tranche d'âge introuvable."));

    Course course = new Course();
    course.setName(dto.getName());
    course.setDescription(dto.getDescription());
    course.setMaxParticipants(dto.getMaxParticipants());
    course.setStartDate(dto.getStartDate());
    course.setEndDate(dto.getEndDate());
    course.setCoach(coach);
    course.setCourseType(courseType);
    course.setAgeRange(ageRange);
    course.setCancelled(false);

    return courseDao.save(course);
  }
}
