package com.k9club.api.dao;

import com.k9club.api.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for {@link Dog} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * and declares custom query methods to ensure dogs are accessed
 * in the context of their owning user.
 */
@Repository
public interface DogDao extends JpaRepository<Dog, Long> {

  /**
   * Retrieves a dog by its unique identifier and owner identifier.
   * <p>
   * Ensures that the returned {@link Dog} is owned by the specified user.
   *
   * @param dogId   the unique ID of the dog
   * @param ownerId the unique ID of the owner
   * @return an {@link Optional} containing the {@link Dog} if found and owned by the given user,
   * or empty if no such dog exists
   */
  Optional<Dog> findByIdAndOwnerId(Long dogId, Long ownerId);

  /**
   * Retrieves all dogs belonging to a specific owner.
   * <p>
   * Allows enumeration of a user's dogs without loading all dogs in the system.
   *
   * @param ownerId the unique ID of the owner
   * @return a {@link List} of {@link Dog} entities owned by the specified user,
   * or an empty list if the user has no dogs
   */
  List<Dog> findByOwnerId(Long ownerId);
}
