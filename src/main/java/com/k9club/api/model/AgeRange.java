package com.k9club.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

/**
 * JPA entity representing an age range for courses.
 * <p>
 * Defines the minimum and maximum age limits, tracks associated courses,
 * and stores auditing timestamps.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AgeRange {

  // TODO FAIRE LES JSON VIEWS ET AJOUTER AU CONTROLLER

  /**
   * Primary key identifier for the age range.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  //
  // TODO FAIRE UN VALIDATEUR POUR QUE maxAge SOIT SUPERIEUR A minAge
  //

  /**
   * Minimum age (inclusive) for this range.
   * Must not be null.
   */
  @NotNull
  @Column(nullable = false)
  protected Integer minAge;

  /**
   * Maximum age (inclusive) for this range.
   * Must not be null.
   */
  @NotNull
  @Column(nullable = false)
  protected Integer maxAge;

  /**
   * Timestamp marking when the age range record was created.
   * <p>
   * Automatically populated on insert and not updatable thereafter.
   */
  @CreatedDate
  @Column(updatable = false, nullable = false)
  protected Instant createdAt;

  /**
   * Timestamp marking the last time the age range record was modified.
   * <p>
   * Automatically updated on each save.
   */
  @LastModifiedDate
  protected Instant updatedAt;


  // TODO REVOIR CE COMMENTAIRE JAVA DOC ET VOIR CELUI DANS COURSETYPE POUR LA LISTE DES COURS
  /**
   * List of courses that fall within this age range.
   * <p>
   * One-to-many relationship mapped by the "ageRange" field in Course.
   */
  @OneToMany(mappedBy = "ageRange")
  protected List<Course> courses;
}
