package com.k9club.api.model;

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
  protected Long id;

  /**
   * Name of the dog; must be between 1 and 100 characters.
   */
  @NotBlank
  @Column(nullable = false, length = 100)
  protected String name;

  /**
   * Date of birth of the dog.
   */
  @NotBlank
  @Column(nullable = false)
  protected LocalDate birthday;

  /**
   * Gender of the dog (e.g., "Male" or "Female").
   */
  @NotBlank
  @Column(nullable = false)
  protected String gender;

  /**
   * Timestamp marking when the dog record was created.
   * <p>
   * Automatically populated on insert and never updated thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private Instant createdAt;

  /**
   * Timestamp marking the last time the dog record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  private Instant updatedAt;

  //  relation with User (possessed)
  /**
   * Owner of the dog.
   * <p>
   * Many-to-one relationship to the {@link User} entity; cannot be null.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  // relation with Breed (belongs_to)
  /**
   * Breed of the dog.
   * <p>
   * Many-to-one relationship to the {@link Breed} entity; cannot be null.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "breed_id", nullable = false)
  private Breed breed;
}
