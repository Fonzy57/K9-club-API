package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
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
 * Stores basic dog information (name, birthdate, gender), auditing timestamps,
 * and relationships to its owner, breed, and registrations.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Dog {

  // TODO FAIRE LES JSON VIEWS ET AJOUTER AU CONTROLLER

  /**
   * Primary key identifier for the dog.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  /**
   * Name of the dog.
   * <p>
   * Must not be blank and length between 2 and 100 characters.
   * Validation message: "Le nom du chien est obligatoire" or
   * "Le nom du chien doit être compris entre 2 et 100 caractères".
   */
  @NotBlank(message = "Le nom du chien est obligatoire")
  @Length(min = 2, max = 100, message = "Le nom du chien doit être compris entre 2 et 100 caractères")
  @Column(nullable = false)
  protected String name;

  /**
   * Date of birth of the dog.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "La date de naissance du chien est obligatoire")
  @Column(nullable = false)
  protected LocalDate birthdate;

  // TODO VOIR POUR LE GENRE, PEUT ETRE FAIRE UN ENUM
  /**
   * Gender of the dog (e.g., "Male" or "Female").
   * <p>
   * Must not be blank.
   */
  @NotBlank(message = "Le genre du chien est obligatoire")
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
   * Uses {@code @JsonBackReference} to prevent circular serialization.
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
   * Uses {@code @JsonIgnoreProperties} to avoid serializing the breed’s dog list.
   */
  @ManyToOne(optional = false)
  @JoinColumn(name = "breed_id", nullable = false)
  @JsonIgnoreProperties("dogs")
  protected Breed breed;


  // relation avec registration
  // TODO revoir le mappedBy et le orphanRemoval
  // @JsonIgnoreProperties("dog")
  /**
   * Registrations associated with this dog.
   * <p>
   * One-to-many relationship to {@link Registration}; orphan removal enabled.
   */
  @OneToMany(mappedBy = "dog", orphanRemoval = true)
  protected List<Registration> registrations = new ArrayList<>();
}
