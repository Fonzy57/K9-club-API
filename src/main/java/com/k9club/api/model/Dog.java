package com.k9club.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Dog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(nullable = false, length = 100)
  protected String name;

  @Column(nullable = false)
  protected LocalDate birthday;

  // TODO NULLABLE SI JAMAIS CHOIX 'AUTRE FAIT'
  @Column(nullable = true)
  protected String gender;

  @Column(nullable = false)
  protected LocalDateTime createdAt;

  // TODO ICI A NE PAS RENTRER DANS LA BDD
  // CAR CALCULER A PARTIR DE LA DATE DE NAISSANCE
  @Transient
  protected int age; // TODO PAS SUR DU int CAR SI MOINS D'UN AN CA POSE PROBLÈME
}
