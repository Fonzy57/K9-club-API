package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsCoach;
import com.k9club.api.jsonview.ViewsOwner;
import com.k9club.api.jsonview.ViewsUser;
import com.k9club.api.model.enums.CourseRegistrationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

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

  /**
   * Primary key identifier for the registration.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class,
      ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class,
      ViewsOwner.CourseRegistrationInfo.class, ViewsAdmin.CourseTypeInfo.class, ViewsCoach.CourseTypeInfo.class,
      ViewsAdmin.DogsInfo.class, ViewsAdmin.AgeRangeInfo.class, ViewsCoach.AgeRangeInfo.class, ViewsOwner.CourseInfo.class})
  protected Long id;

  /**
   * Current status of the registration.
   * <p>
   * Must be one of CONFIRMED, CANCELLED, or PENDING.
   */
  @NotNull(message = "Le status de la réservation est obligatoire")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "ENUM('CONFIRMED', 'CANCELLED_BY_OWNER', 'CANCELLED_BY_ADMIN')")
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class,
      ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class,
      ViewsOwner.CourseRegistrationInfo.class, ViewsAdmin.CourseTypeInfo.class, ViewsCoach.CourseTypeInfo.class,
      ViewsAdmin.DogsInfo.class, ViewsAdmin.AgeRangeInfo.class, ViewsCoach.AgeRangeInfo.class, ViewsOwner.CourseInfo.class})
  protected CourseRegistrationStatus status;

  /**
   * Timestamp marking when the registration record was created.
   * <p>
   * Automatically populated on insert and never updated thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class,
      ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class,
      ViewsOwner.CourseRegistrationInfo.class, ViewsAdmin.CourseTypeInfo.class, ViewsCoach.CourseTypeInfo.class,
      ViewsAdmin.DogsInfo.class, ViewsAdmin.AgeRangeInfo.class, ViewsCoach.AgeRangeInfo.class})
  protected Instant createdAt;

  /**
   * Timestamp marking the last time the registration record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class,
      ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class,
      ViewsOwner.CourseRegistrationInfo.class, ViewsAdmin.CourseTypeInfo.class, ViewsCoach.CourseTypeInfo.class,
      ViewsAdmin.DogsInfo.class, ViewsAdmin.AgeRangeInfo.class, ViewsCoach.AgeRangeInfo.class})
  protected Instant updatedAt;

  // TODO LIAISON AVEC L'ENTITE DOG
  /**
   * Dog associated with this registration.
   * <p>
   * Many-to-one relationship to the Dog entity; required.
   */
  // @JsonIgnoreProperties("registrations")
  @NotNull(message = "Le chien doit être renseigné.")
  @ManyToOne(optional = false)
  @JoinColumn(name = "dog_id", nullable = false)
  @JsonView({ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class, ViewsAdmin.CourseRegistrationInfo.class,
      ViewsCoach.CourseRegistrationInfo.class, ViewsOwner.CourseRegistrationInfo.class,
      ViewsAdmin.CourseTypeInfo.class, ViewsCoach.CourseTypeInfo.class, ViewsAdmin.AgeRangeInfo.class,
      ViewsCoach.AgeRangeInfo.class, ViewsOwner.CourseInfo.class})
  protected Dog dog;

  // TODO LIAISON AVEC L'ENTITE COURSE
  /**
   * Course associated with this registration.
   * <p>
   * Many-to-one relationship to the Course entity; required.
   */
  // @JsonIgnoreProperties("registrations")
  @NotNull(message = "Un cours doit être choisi.")
  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class,
      ViewsOwner.CourseRegistrationInfo.class, ViewsAdmin.DogsInfo.class})
  protected Course course;
}
