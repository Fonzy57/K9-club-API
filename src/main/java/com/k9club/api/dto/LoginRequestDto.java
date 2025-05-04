package com.k9club.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) used to receive user login credentials.
 * <p>
 * Encapsulates the necessary fields (email and password) required for authentication.
 * Ensures validation of the input data before processing the login request.
 */
@Getter
@Setter
public class LoginRequestDto {

  /**
   * The user's email address.
   * <p>
   * Must not be blank and must be a valid email format.
   */
  @NotBlank
  @Email
  private String email;

  /**
   * The user's password.
   * <p>
   * Must not be blank.
   */
  @NotBlank
  private String password;
}
