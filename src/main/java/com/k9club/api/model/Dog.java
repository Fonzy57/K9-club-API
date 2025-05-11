package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.views.ViewUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

/**
 * JPA entity representing a dog in the K9 Club application.
 * <p>
 * Stores basic dog information (name, birthday, gender), auditing timestamps,
 * and relationships to its owner and breed.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Dog {

  /**
   * Primary key identifier for the dog.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUser.Owner.class)
  protected Long id;

  /**
   * Name of the dog; must be between 1 and 100 characters.
   */
  @NotBlank
  @Column(nullable = false, length = 100)
  @JsonView(ViewUser.Owner.class)
  protected String name;

  /**
   * Date of birth of the dog.
   */
  @NotBlank
  @Column(nullable = false)
  @JsonView(ViewUser.Owner.class)
  protected LocalDate birthday;

  /**
   * Gender of the dog (e.g., "Male" or "Female").
   */
  @NotBlank
  @Column(nullable = false)
  @JsonView(ViewUser.Owner.class)
  protected String gender;

  /**
   * Timestamp marking when the dog record was created.
   * <p>
   * Automatically populated on insert and never updated thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  @JsonView(ViewUser.Owner.class)
  private Instant createdAt;

  /**
   * Timestamp marking the last time the dog record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  @JsonView(ViewUser.Owner.class)
  private Instant updatedAt;

  //  relation with User (possessed)
  /**
   * Owner of the dog.
   * <p>
   * Many-to-one relationship to the {@link User} entity; cannot be null.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference("user-dogs") // ← n’essaie pas de sérialiser owner à nouveau
  private User owner; // TODO VOIR SI JE GARDE JsonBackReference OU SI JE CHANGE POUR UN JSONVIEW

  // relation with Breed (belongs_to)
  /**
   * Breed of the dog.
   * <p>
   * Many-to-one relationship to the {@link Breed} entity; cannot be null.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "breed_id", nullable = false)
  @JsonIgnoreProperties("dogs")
  @JsonView(ViewUser.Owner.class)
  private Breed breed;
}
