package com.k9club.api.dto.course;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Data Transfer Object used to create a new Course.
 * <p>
 * Carries all necessary information for course creation,
 * including name, description, capacity, schedule, and references
 * to coach, course type, and age range.
 */
@Getter
@Setter
public class CourseCreateDto {

  /**
   * Name of the course.
   * <p>
   * Must not be blank and must contain between 5 and 50 characters.
   */
  @NotBlank(message = "Le nom est obligatoire")
  @Size(min = 5, max = 50, message = "Le nom doit contenir entre 5 et 50 caractères")
  private String name;

  /**
   * Detailed description of the course.
   * <p>
   * Must not be blank and must contain between 20 and 1000 characters.
   */
  @NotBlank(message = "La description est obligatoire")
  @Size(min = 20, max = 1000, message = "La description doit contenir entre 20 et 1000 caractères")
  private String description;

  /**
   * Maximum number of participants allowed in the course.
   * <p>
   * Must not be null and must be between 1 and 20.
   */
  @NotNull(message = "Le nombre de participants maximum est obligatoire")
  @Min(value = 1, message = "Le nombre minimum de participants est 1")
  @Max(value = 20, message = "Le nombre maximum de participants est 20")
  private Integer maxParticipants;

  /**
   * Scheduled start date and time for the course.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "La date de début est obligatoire")
  private Instant startDate;

  /**
   * Scheduled end date and time for the course.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "La date de fin est obligatoire")
  private Instant endDate;

  /**
   * Identifier of the coach assigned to this course.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "Le coach est obligatoire")
  private Long coachId;

  /**
   * Identifier of the course type for this course.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "Le type de cours est obligatoire")
  private Long courseTypeId;

  /**
   * Identifier of the age range applicable for this course.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "La tranche d'âge est obligatoire")
  private Long ageRangeId;
}
