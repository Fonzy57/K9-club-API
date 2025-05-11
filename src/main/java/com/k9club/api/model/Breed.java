package com.k9club.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.k9club.api.views.ViewUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a dog breed in the K9 Club application.
 * <p>
 * Stores the breed name and a list of associated Dog entities.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Breed {

  /**
   * Primary key identifier for the breed.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(ViewUser.Owner.class)
  protected Long id;

  /**
   * Name of the breed; must not be null and up to 100 characters.
   */
  @Column(nullable = false, length = 100)
  @JsonView(ViewUser.Owner.class)
  protected String name;

  /**
   * List of dogs belonging to this breed.
   * <p>
   * One-to-many relationship with Dog; orphanRemoval ensures that
   * deleting a breed will remove its associated dogs.
   */
  @OneToMany(mappedBy = "breed", orphanRemoval = true)
  @JsonView(ViewUser.Owner.class)
  protected List<Dog> dogs = new ArrayList<>();
}
