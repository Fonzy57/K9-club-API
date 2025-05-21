package com.k9club.api.dao;

import com.k9club.api.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRegistrationDao extends JpaRepository<CourseRegistration, Long> {
}