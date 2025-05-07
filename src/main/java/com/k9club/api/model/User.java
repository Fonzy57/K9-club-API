package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.views.ViewUserAdmin;
import com.k9club.api.views.ViewUserSuperAdmin;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUserAdmin.class)
  protected Long id;

  @NotBlank
  @Column(nullable = false)
  @Length(min = 3, max = 100)
  @JsonView(ViewUserAdmin.class)
  protected String firstname;

  @NotBlank
  @Column(nullable = false)
  @Length(min = 3, max = 100)
  @JsonView(ViewUserAdmin.class)
  protected String lastname;

  @NotBlank
  @Email
  @Column(unique = true, nullable = false)
  @JsonView(ViewUserAdmin.class)
  protected String email;

  @NotBlank
  @Column(nullable = false)
  protected String password;

//  TODO VOIR POUR AJOUTER LE NUMERO DE TEL => PEUT ETRE NULL SI JE L'AJOUTE

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "ENUM('SUPER_ADMIN','ADMIN','COACH', 'OWNER')")
  @JsonView(ViewUserSuperAdmin.class)
  protected UserRole userRole;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  @JsonView(ViewUserAdmin.class)
  private Instant createdAt;

  @LastModifiedDate
  @JsonView(ViewUserAdmin.class)
  private Instant updatedAt;
}
