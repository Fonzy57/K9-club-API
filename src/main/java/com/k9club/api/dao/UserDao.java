package com.k9club.api.dao;

import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object for {@link User} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * and declares custom query methods for finding users by email, role, or both.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

  /**
   * Finds a user by their email address.
   *
   * @param email the email address to search for
   * @return an {@link Optional} containing the {@link User} if found, or empty if not
   */
  Optional<User> findByEmail(String email);

  /**
   * Retrieves all users assigned the specified role.
   *
   * @param role the {@link UserRole} to filter by
   * @return a {@link List} of {@link User} objects with the given role
   */
  List<User> findByUserRole(UserRole role);

  /**
   * Finds a user by their ID and role.
   *
   * @param id       the unique identifier of the user
   * @param userRole the {@link UserRole} to match
   * @return an {@link Optional} containing the {@link User} if found and role matches, or empty if not
   */
  Optional<User> findByIdAndUserRole(Long id, UserRole userRole);
}
