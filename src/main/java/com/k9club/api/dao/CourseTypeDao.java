package com.k9club.api.dao;

import com.k9club.api.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeDao extends JpaRepository<CourseType, Long> {
}
