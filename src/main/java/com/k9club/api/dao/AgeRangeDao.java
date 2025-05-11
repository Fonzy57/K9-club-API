package com.k9club.api.dao;

import com.k9club.api.model.AgeRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRangeDao extends JpaRepository<AgeRange, Long> {
}
