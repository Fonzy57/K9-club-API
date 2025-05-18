package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

  // TODO FAIRE LE DOG CONTROLLER
  // TODO VOIR AUSSI POUR LES VIEWS
  // TODO FAIRE LES JSON VIEWS ET AJOUTER AU CONTROLLER

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
  protected Instant createdAt;

  /**
   * Timestamp marking the last time the dog record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  protected Instant updatedAt;

  //  relation with User (possessed)
  /**
   * Owner of the dog.
   * <p>
   * Many-to-one relationship to the {@link User} entity; cannot be null.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference("user-dogs") // ← n’essaie pas de sérialiser owner à nouveau
  protected User owner; // TODO VOIR SI JE GARDE JsonBackReference OU SI JE CHANGE POUR UN JSONVIEW

  // relation with Breed (belongs_to)
  /**
   * Breed of the dog.
   * <p>
   * Many-to-one relationship to the {@link Breed} entity; cannot be null.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "breed_id", nullable = false)
  @JsonIgnoreProperties("dogs")
  protected Breed breed;


  // relation avec registration
  // TODO revoir le mappedBy
  // @JsonIgnoreProperties("dog")
  @OneToMany(mappedBy = "dog", orphanRemoval = true)
  protected List<Registration> registrations = new ArrayList<>();
}
