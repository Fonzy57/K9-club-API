package com.k9club.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public abstract class UserBasicUpdateDto {
  @NotBlank(message = "Le prénom est obligatoire")
  @Length(min = 3, max = 100)
  protected String firstname;

  @NotBlank(message = "Le nom est obligatoire")
  @Length(min = 3, max = 100)
  protected String lastname;

  @NotBlank(message = "L'adresse email est obligatoire")
  @Email(message = "L'adresse email est invalide")
  protected String email;
}
