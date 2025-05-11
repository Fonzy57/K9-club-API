package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.views.ViewUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * JPA entity representing a course type in the K9 Club application.
 * <p>
 * Defines styling properties (name, text color, background color)
 * that can be applied to Course entities once created.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CourseType {

  /**
   * Primary key identifier for the course type.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUser.Owner.class)
  protected Long id;

  /**
   * Unique name of the course type (e.g., "Agility", "Obedience").
   */
  @NotBlank
  @Column(unique = true, nullable = false)
  @Length(min = 5, max = 25)
  @JsonView(ViewUser.Owner.class)
  protected String name;

  /**
   * Hexadecimal color code in #RRGGBB format used for rendering the text color of this course type.
   * Must be exactly 7 characters long and match the pattern #RRGGBB.
   */
  @NotBlank
  @Column(unique = true, nullable = false)
  @Length(min = 7, max = 7)
  @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Doit être au format #RRGGBB")
  @JsonView(ViewUser.Owner.class)
  protected String textColor;

  /**
   * Hexadecimal color code in #RRGGBB format used for rendering the background color of this course type.
   * Must be exactly 7 characters long and match the pattern #RRGGBB.
   */
  @NotBlank
  @Column(unique = true, nullable = false)
  @Length(min = 7, max = 7)
  @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Doit être au format #RRGGBB")
  @JsonView(ViewUser.Owner.class)
  protected String backgroundColor;

}
