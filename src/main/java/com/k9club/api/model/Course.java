package com.k9club.api.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.jsonview.ViewsUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a course offering in the K9 Club application.
 * <p>
 * Stores course metadata (name, description, participant limit, schedule),
 * auditing timestamps, and relationships to coach, type, age range, and registrations.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course {

  // TODO FAIRE LES JSON VIEWS ET AJOUTER AU CONTROLLER

  /**
   * Primary key identifier for the course.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView({ViewsUser.Owner.class})
  protected Long id;

  /**
   * Title of the course.
   * <p>
   * Must not be blank. Validation message: "Le nom est obligatoire".
   */
  @NotBlank(message = "Le nom est obligatoire")
  @Column(nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected String name;

  /**
   * Detailed description of the course.
   * <p>
   * Stored as TEXT. Must not be blank. Validation message: "La description est obligatoire".
   */
  @NotBlank(message = "La description est obligatoire")
  @Column(nullable = false, columnDefinition = "TEXT")
  @JsonView({ViewsUser.Owner.class})
  protected String description;

  /**
   * Maximum number of participants allowed in the course.
   * <p>
   * Must not be null. Validation message: "Le nombre de participants maximum est obligatoire".
   */
  @NotNull(message = "Le nombre de participants maximum est obligatoire")
  @Column(nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected Integer maxParticipants;

  //
  // TODO FAIRE UN VALIDATEUR POUR QUE END DATE SOIT SUPERIEUR A START DATE
  //


  /**
   * Start date and time of the course session.
   * <p>
   * Must not be null. Validation message: "La date de début est obligatoire".
   */
  @NotNull(message = "La date de début est obligatoire")
  @Column(nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected LocalDateTime startDate;

  /**
   * End date and time of the course session.
   * <p>
   * Must not be null. Validation message: "La date de fin est obligatoire".
   */
  @NotNull(message = "La date de fin est obligatoire")
  @Column(nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected LocalDateTime endDate;

  /**
   * Timestamp when the course record was created.
   * <p>
   * Automatically set on insert and never modified thereafter.
   */
  @CreatedDate
  @Column(nullable = false)
  protected Instant createdAt;

  /**
   * Timestamp when the course record was last updated.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  protected Instant updatedAt;

  // LIAISON AVEC UN USER SI ROLE COACH
  /**
   * Coach assigned to this course.
   * <p>
   * Many-to-one relationship to the User entity; must not be null.
   * Validation message: "Le coach est obligatoire".
   */

  // @JsonIgnoreProperties("courses")
  @NotNull(message = "Le coach est obligatoire")
  @ManyToOne()
  @JoinColumn(name = "user_id", nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected User coach;

  // LIAISON AVEC L'ENTITE COURSETYPE
  /**
   * Type of the course (e.g., Agility, Obedience).
   * <p>
   * Many-to-one relationship to the CourseType entity; must not be null.
   * Validation message: "Le type de cours est obligatoire".
   */
  @NotNull(message = "Le type de cours est obligatoire")
  @ManyToOne(optional = false)
  @JoinColumn(name = "course_type_id", nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected CourseType courseType;

  // LIAISON AVEC L'ENTITE AGE RANGE
  /**
   * Age range applicable for this course.
   * <p>
   * Many-to-one relationship to the AgeRange entity; must not be null.
   * Validation message: "La tranche d'âge est obligatoire".
   */
  @NotNull(message = "La tranche d'âge est obligatoire")
  @ManyToOne(optional = false)
  @JoinColumn(name = "age_range_id", nullable = false)
  @JsonView({ViewsUser.Owner.class})
  protected AgeRange ageRange;

  /**
   * Registrations made for this course.
   * <p>
   * One-to-many relationship to Registration; orphan removal is enabled to delete
   * registrations when a course is removed.
   */
  @OneToMany(
      mappedBy = "course",
      //cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  protected List<CourseRegistration> registrations = new ArrayList<>();
}
