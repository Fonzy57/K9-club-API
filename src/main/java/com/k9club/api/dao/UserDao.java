package com.k9club.api.dao;

import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  List<User> findByUserRole(UserRole role);
}
