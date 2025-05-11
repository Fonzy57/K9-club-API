package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.views.ViewUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
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
public class Type {

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
  @JsonView(ViewUser.Owner.class)
  protected String name;

  /**
   * CSS color value used for rendering text when displaying this course type.
   */
  @NotBlank
  @Column(unique = true, nullable = false)
  @JsonView(ViewUser.Owner.class)
  protected String textColor;

  /**
   * CSS color value used for rendering the background when displaying this course type.
   */
  @NotBlank
  @Column(unique = true, nullable = false)
  @JsonView(ViewUser.Owner.class)
  protected String backgroundColor;

}
