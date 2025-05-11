package com.k9club.api.dao;

import com.k9club.api.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationDao extends JpaRepository<Registration, Long> {
}