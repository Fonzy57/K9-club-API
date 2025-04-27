package com.k9club.api.model;

import com.k9club.api.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class User {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotBlank
  @Column(nullable = false)
  @Length(min = 3, max = 100)
  protected String firstname;

  @NotBlank
  @Column(nullable = false)
  @Length(min = 3, max = 100)
  protected String lastname;

  @NotBlank
  @Email
  @Column(unique = true, nullable = false)
  protected String email;

  @NotBlank
  @Column(nullable = false)
  protected String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "ENUM('ADMIN','COACH', 'USER')")
  protected Role role;
}
