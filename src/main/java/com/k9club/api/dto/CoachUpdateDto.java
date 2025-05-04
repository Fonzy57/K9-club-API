package com.k9club.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CoachUpdateDto {
  @NotBlank
  @Length(min = 3, max = 100)
  protected String firstname;

  @NotBlank
  @Length(min = 3, max = 100)
  protected String lastname;

  @NotBlank
  @Email
  protected String email;
}
