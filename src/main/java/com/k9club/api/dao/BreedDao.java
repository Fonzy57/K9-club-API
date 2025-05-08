package com.k9club.api.dao;

import com.k9club.api.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedDao extends JpaRepository<Breed, Long> {
}
