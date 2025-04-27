package com.k9club.api.dao;

import com.k9club.api.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogDao extends JpaRepository<Dog, Integer> {
}
