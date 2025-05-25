package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.jsonview.ViewsAdmin;
import com.k9club.api.jsonview.ViewsCoach;
import com.k9club.api.jsonview.ViewsOwner;
import com.k9club.api.jsonview.ViewsUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a course type in the K9 Club application.
 * <p>
 * Defines styling properties (name, text color, background color)
 * that can be applied to Course entities.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CourseType {

  /**
   * Unique identifier for the course type.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class,
      ViewsOwner.CourseInfo.class, ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class, ViewsOwner.CourseRegistrationInfo.class})
  protected Long id;

  /**
   * Name of the course type (e.g., "Agility", "Obedience").
   * <p>
   * Must not be blank and length must be between 3 and 25 characters.
   */
  @NotBlank(message = "Le nom du type est obligatoire")
  @Column(unique = true, nullable = false)
  @Length(min = 3, max = 25)
  @JsonView({ViewsUser.Owner.class, ViewsAdmin.CourseInfo.class, ViewsCoach.CourseInfo.class,
      ViewsOwner.CourseInfo.class, ViewsAdmin.CourseRegistrationInfo.class, ViewsCoach.CourseRegistrationInfo.class, ViewsOwner.CourseRegistrationInfo.class})
  protected String name;

  /**
   * Text color for the course type in hexadecimal #RRGGBB format.
   * <p>
   * Must not be blank, exactly 7 characters, and match the pattern #RRGGBB.
   */
  @NotBlank(message = "La couleur du texte du type est obligatoire")
  @Column(unique = true, nullable = false)
  @Length(min = 7, max = 7, message = "La couleur doit être au format #RRGGBB, 7 caractères en comptant le '#'")
  @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "La couleur doit être au format #RRGGBB")
  @JsonView({ViewsUser.Owner.class, ViewsOwner.CourseInfo.class, ViewsAdmin.CourseRegistrationInfo.class,
      ViewsCoach.CourseRegistrationInfo.class, ViewsOwner.CourseRegistrationInfo.class})
  protected String textColor;

  /**
   * Background color for the course type in hexadecimal #RRGGBB format.
   * <p>
   * Must not be blank, exactly 7 characters, and match the pattern #RRGGBB.
   */
  @NotBlank(message = "La couleur de fond du type est obligatoire")
  @Column(unique = true, nullable = false)
  @Length(min = 7, max = 7, message = "La couleur doit être au format #RRGGBB, 7 caractères en comptant le '#'")
  @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "La couleur doit être au format #RRGGBB")
  @JsonView({ViewsUser.Owner.class, ViewsOwner.CourseInfo.class, ViewsAdmin.CourseRegistrationInfo.class,
      ViewsCoach.CourseRegistrationInfo.class, ViewsOwner.CourseRegistrationInfo.class})
  protected String backgroundColor;

  /**
   * Timestamp marking when the course type record was created.
   * <p>
   * Automatically populated on insert and not updatable thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  protected Instant createdAt;

  /**
   * Timestamp marking the last time the course type record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  protected Instant updatedAt;

  /**
   * List of courses associated with this course type.
   * <p>
   * Represents a one-to-many relationship where each {@link Course} is assigned
   * to a single {@link CourseType}, but a {@link CourseType} can be linked to multiple courses.
   * <p>
   * This association allows retrieval of all courses that share the same category,
   * such as "Agility", "Puppy training", or "Obedience".
   */
  @OneToMany(mappedBy = "courseType")
  private List<Course> courses = new ArrayList<>();

}
