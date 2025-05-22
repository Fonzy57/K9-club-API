package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.k9club.api.model.enums.UserRole;
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
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a user of the K9 Club application.
 * <p>
 * Stores personal information, authentication credentials, user role,
 * auditing timestamps, and associated dogs for OWNER users.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

  // TODO FAIRE LES JSON VIEWS ET AJOUTER AU CONTROLLER

  /**
   * Primary key identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  /**
   * User’s first name; must be between 3 and 100 characters.
   */
  @NotBlank
  @Column(nullable = false)
  @Length(min = 3, max = 100)
  protected String firstname;

  /**
   * User’s last name; must be between 3 and 100 characters.
   */
  @NotBlank
  @Column(nullable = false)
  @Length(min = 3, max = 100)
  protected String lastname;

  /**
   * User’s email address; must be unique and a valid email format.
   */
  @NotBlank
  @Email
  @Column(unique = true, nullable = false)
  protected String email;

  /**
   * Hashed password for authentication.
   * <p>
   * Not exposed in any JSON views.
   */
  @NotBlank
  @Column(nullable = false)
  // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  // TODO VOIR CETTE ANNOTATION JsonProperty doc : https://fasterxml.github.io/jackson-annotations/javadoc/2.6/com/fasterxml/jackson/annotation/JsonProperty.Access.html
  protected String password;


  /**
   * Role assigned to the user, determining access permissions.
   * <p>
   * Stored as STRING in the database.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "ENUM('SUPER_ADMIN','ADMIN','COACH', 'OWNER')")
  protected UserRole userRole;

  /**
   * Timestamp marking when the user was created.
   * <p>
   * Automatically populated on insert and never updated thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  protected Instant createdAt;

  /**
   * Timestamp marking the last time the user record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  protected Instant updatedAt;


  // ASSOCIATION WITH DOG
  /**
   * List of dogs owned by this user.
   * <p>
   * Only returned in the OWNER view; orphans are removed automatically.
   */
  @OneToMany(mappedBy = "owner", orphanRemoval = true)
  @JsonManagedReference("user-dogs")
  protected List<Dog> dogs = new ArrayList<>();

  //  TODO VOIR POUR AJOUTER LE NUMERO DE TEL => PEUT ETRE NULL SI JE L'AJOUTE

  //  TODO AJOUTER UN BOOLEAN POUR L'ANONYMISATION DES DONNEES
}
