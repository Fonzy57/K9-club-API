package com.k9club.api.dto.courseRegistration;

import com.k9club.api.model.enums.CourseRegistrationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for creating a new CourseRegistration.
 * <p>
 * Carries the necessary information to initialize a registration,
 * including its status, the dog being registered, and the course.
 */
@Getter
@Setter
public class CourseRegistrationCreateDto {

  /**
   * Initial status of the registration.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "Le status de la réservation est obligatoire")
  private CourseRegistrationStatus status;

  /**
   * Identifier of the dog to register for the course.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "Le chien doit être renseigné pour pouvoir faire une réservation")
  private Long dogId;

  /**
   * Identifier of the course for which the dog is being registered.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "Le cours doit être choisi pour pouvoir faire une réservation")
  private Long courseId;

}
