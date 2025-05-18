package com.k9club.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a dog breed in the K9 Club application.
 * <p>
 * Stores the unique breed name and the list of associated Dog entities.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Breed {

  // TODO FAIRE LES JSON VIEWS

  /**
   * Primary key identifier for the breed.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  /**
   * Unique name of the breed.
   * <p>
   * Must not be blank and must contain between 5 and 100 characters.
   * Validation messages:
   * - "Le nom est obligatoire" if blank
   * - "Le nom doit contenir entre 5 et 100 caractères" if outside length bounds
   */
  @NotBlank(message = "Le nom est obligatoire")
  @Size(min = 5, max = 100, message = "La nom doit contenir entre 5 et 100 caractères")
  @Column(nullable = false, length = 100, unique = true)
  protected String name;

  /**
   * List of dogs belonging to this breed.
   * <p>
   * One-to-many relationship with {@link Dog}; orphanRemoval is not enabled here
   * to preserve historic data if a breed is removed.
   */
  @OneToMany(mappedBy = "breed")
  protected List<Dog> dogs = new ArrayList<>();
}
