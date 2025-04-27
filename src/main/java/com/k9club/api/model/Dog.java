package com.k9club.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Dog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Column(nullable = false, length = 100)
  protected String name;

  @Column(nullable = false)
  protected LocalDate birthday;

  // UTILISER UNE ENUM POUR LE GENRE
  // TODO NULLABLE SI JAMAIS CHOIX 'AUTRE FAIT'
  @Column(nullable = true)
  protected String gender;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;

  // TODO ICI A NE PAS RENTRER DANS LA BDD
  // CAR CALCULER A PARTIR DE LA DATE DE NAISSANCE
  @Transient
  protected String age; // TODO PAS SUR DU int CAR SI MOINS D'UN AN CA POSE PROBLÈME

  // TODO DEPLACER SUREMENT CETTE METHODE DANS LE CONTROLEUR
  @Transient
  public String getAge() {
    Period period = Period.between(birthday, LocalDate.now());

    if (period.getYears() >= 1) {
      return period.getYears() + " an" + (period.getYears() > 1 ? "s" : "");
    } else {
      return period.getMonths() + " mois";
    }
  }
}
