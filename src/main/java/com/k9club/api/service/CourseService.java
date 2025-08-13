package com.k9club.api.service;

import com.k9club.api.dto.course.CourseCreateDto;
import com.k9club.api.model.Course;

/**
 * Service interface for managing {@link Course} entities.
 * <p>
 * Defines business operations related to courses, separate from controller
 * logic and persistence layer.
 */
public interface CourseService {
  /**
   * Creates and persists a new {@link Course} from the provided data.
   * <p>
   * Implementations must perform the necessary business validations and
   * related-entity lookups.
   *
   * @param courseCreateDto data required to create a course
   * @return the persisted {@link Course}
   * @throws org.springframework.web.server.ResponseStatusException if a validation fails (e.g. 400/404)
   */
  Course addCourse(CourseCreateDto courseCreateDto);
}
