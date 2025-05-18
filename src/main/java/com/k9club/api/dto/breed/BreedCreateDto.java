package com.k9club.api.dto.breed;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for creating a new Breed.
 * <p>
 * Encapsulates the information required to create a Breed entity.
 */
@Getter
@Setter
public class BreedCreateDto {

  /**
   * Name of the breed.
   * <p>
   * Must not be blank and must contain between 5 and 100 characters.
   */
  @NotBlank(message = "Le nom est obligatoire")
  @Size(min = 5, max = 100, message = "Le nom doit contenir entre 5 et 100 caractères")
  private String name;

}
