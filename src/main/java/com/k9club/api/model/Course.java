package com.k9club.api.model;


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

  // TODO AJOUTER LES JSON VIEW

  // TODO

  /**
   * Primary key identifier for the course.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  /**
   * Title of the course; must not be blank.
   */
  @NotBlank
  @Column(nullable = false)
  protected String name;

  /**
   * Detailed description of the course; stored as TEXT.
   */
  @NotBlank
  @Column(nullable = false, columnDefinition = "TEXT")
  protected String description;

  /**
   * Maximum number of participants allowed in the course.
   */
  @NotNull
  @Column(nullable = false)
  protected Integer maxParticipants;

  /**
   * Start date and time of the course session.
   */
  @NotNull
  @Column(nullable = false)
  protected LocalDateTime startDate;

  /**
   * End date and time of the course session.
   */
  @NotNull
  @Column(nullable = false)
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
   * Many-to-one relationship to the User entity; may be null if unassigned.
   */
  // @JsonIgnoreProperties("courses")
  @ManyToOne()
  @JoinColumn(name = "user_id")
  protected User coach;

  // LIAISON AVEC L'ENTITE COURSETYPE
  /**
   * Type of the course (e.g., Agility, Obedience).
   * <p>
   * Many-to-one relationship to the CourseType entity; required.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "course_type_id", nullable = false)
  protected CourseType courseType;

  // LIAISON AVEC L'ENTITE AGE RANGE
  /**
   * Age range applicable for this course.
   * <p>
   * Many-to-one relationship to the AgeRange entity; required.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "age_range_id", nullable = false)
  protected AgeRange ageRange;

  /**
   * Registrations made for this course.
   * <p>
   * One-to-many relationship to Registration; orphan removals cascade.
   */
  @OneToMany(
      mappedBy = "course",
      //cascade = CascadeType.ALL,
      orphanRemoval = true
  )
//  @JsonIgnoreProperties("course")
//  @JsonView(ViewUser.Owner.class)
  protected List<Registration> registrations = new ArrayList<>();
}
