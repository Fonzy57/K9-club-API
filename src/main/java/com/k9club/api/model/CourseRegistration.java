package com.k9club.api.model;

import com.k9club.api.model.enums.RegistrationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * JPA entity representing the registration of a dog for a course.
 * <p>
 * Stores the timestamp and status of the registration, auditing information,
 * and links to the associated Dog and Course entities.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CourseRegistration {

  // -----------------------------------------------------

  // TODO VERIFIER LES INFOS ICI ET FAIRE LE CONTROLLER

  // TODO FAIRE LES JSON VIEWS ET AJOUTER AU CONTROLLER

  // -----------------------------------------------------

  /**
   * Primary key identifier for the registration.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  // TODO PAS SÛR D'AVOIR BESOIN DE CETTE DATE
  /**
   * Date and time of the registration.
   */
  @NotNull
  @Column(nullable = false)
  protected LocalDateTime registrationDate;

  /**
   * Current status of the registration.
   * <p>
   * Must be one of CONFIRMED, CANCELLED, or PENDING.
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "ENUM('CONFIRMED','CANCELLED', 'PENDING')")
  protected RegistrationStatus status;

  /**
   * Timestamp marking when the registration record was created.
   * <p>
   * Automatically populated on insert and never updated thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  protected Instant createdAt;

  /**
   * Timestamp marking the last time the registration record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  protected Instant updatedAt;

  // TODO LIAISON AVEC L'ENTITE DOG
  /**
   * Dog associated with this registration.
   * <p>
   * Many-to-one relationship to the Dog entity; required.
   */
  // @JsonIgnoreProperties("registrations")
  @ManyToOne(optional = false)
  @JoinColumn(name = "dog_id", nullable = false)
  protected Dog dog;

  // TODO LIAISON AVEC L'ENTITE COURSE
  /**
   * Course associated with this registration.
   * <p>
   * Many-to-one relationship to the Course entity; required.
   */
  // @JsonIgnoreProperties("registrations")
  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  protected Course course;
}
