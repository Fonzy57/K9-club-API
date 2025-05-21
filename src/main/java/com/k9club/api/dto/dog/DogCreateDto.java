package com.k9club.api.dto.dog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 * Data Transfer Object for creating a new Dog.
 * <p>
 * Carries all required information to register a dog,
 * including its name, birthdate, gender, owner, and breed identifiers.
 */
@Getter
@Setter
public class DogCreateDto {

  /**
   * Name of the dog.
   * <p>
   * Must not be blank and length must be between 2 and 100 characters.
   */
  @NotBlank(message = "Le nom du chien est obligatoire")
  @Length(min = 2, max = 100, message = "Le nom du chien doit être compris entre 2 et 100 caractères")
  private String name;

  /**
   * Birthdate of the dog.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "La date de naissance du chien est obligatoire")
  protected LocalDate birthdate;

  /**
   * Gender of the dog (e.g., "Male" or "Female").
   * <p>
   * Must not be blank.
   */
  @NotBlank(message = "Le genre du chien est obligatoire")
  private String gender;

  /**
   * Identifier of the owner for this dog.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "Le propriétaire du chien doit être renseigné")
  private Long ownerId;

  /**
   * Identifier of the breed for this dog.
   * <p>
   * Must not be null.
   */
  @NotNull(message = "La race du chien doit être renseignée")
  private Long breedId;
}
