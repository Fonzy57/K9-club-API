package com.k9club.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.k9club.api.dao.AgeRangeDao;
import com.k9club.api.dao.CourseDao;
import com.k9club.api.dao.CourseTypeDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.course.CourseCreateDto;
import com.k9club.api.model.Course;
import com.k9club.api.security.interfaces.ISecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Web slice test avec vraie chaîne Spring Security + vrai JwtFilter.
 * - On remplace ISecurityUtils par une implémentation de test (pour contrôler le "subject").
 * - On mocke UserDetailsService pour renvoyer des rôles précis.
 * - On mocke les DAO/service uniquement pour satisfaire l'injection (le filtre bloque/autorise AVANT le contrôleur).
 */
@WebMvcTest(CourseController.class)
@Import(CourseControllerIT.SecurityUtilsTestConfig.class)
class CourseControllerIT {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  // Dépendances exigées par le contrôleur (autres endpoints) — mockées
  @MockitoBean
  CourseDao courseDao;
  @MockitoBean
  CourseTypeDao courseTypeDao;
  @MockitoBean
  AgeRangeDao ageRangeDao;
  @MockitoBean
  UserDao userDao;
  @MockitoBean
  com.k9club.api.service.CourseService courseService;

  // Beans nécessaires quand JPA Auditing est activé dans l’app
  @MockitoBean
  JpaMetamodelMappingContext jpaMetamodelMappingContext;
  @MockitoBean
  AuditorAware<?> auditorAware;

  // On garde le VRAI JwtFilter (il est dans le contexte via @WebMvcTest)
  // -> pas besoin de @MockitoBean JwtFilter

  // On mocke le UserDetailsService car le filtre l'utilise après extraction du subject
  @MockitoBean
  UserDetailsService userDetailsService;

  static class SecurityUtilsTestConfig {
    /**
     * Implémentation de test d'ISecurityUtils :
     * - resolve le token depuis Authorization
     * - getSubjectFromJwt() renvoie un subject "user@example.com" pour tout token non vide
     * (on ne vérifie PAS la signature ici, c'est voulu pour stabiliser le test)
     */
    @Bean
    ISecurityUtils securityUtils() {
      return new ISecurityUtils() {
        @Override
        public String getRole(com.k9club.api.security.AppUserDetails userDetails) {
          // non utilisé ici
          return null;
        }

        @Override
        public String generateToken(com.k9club.api.security.AppUserDetails userDetails) {
          // non utilisé ici
          return null;
        }

        @Override
        public String getSubjectFromJwt(String jwt) {
          // Dans ce test, on considère tout token non vide comme "parsable"
          // et on retourne un subject constant — l'idée est de tester la chaîne de sécu,
          // pas la crypto du JWT (qui est testée ailleurs).
          if (jwt == null || jwt.isBlank()) return null;
          return "user@example.com";
        }
      };
    }
  }

  private String toJson(Object o) throws Exception {
    return objectMapper.writeValueAsString(o);
  }

  private CourseCreateDto validDto() {
    CourseCreateDto dto = new CourseCreateDto();
    dto.setName("Initiation à l'agilité");
    dto.setDescription("Un cours d’initiation à l’agilité destiné aux chiens débutants.");
    dto.setMaxParticipants(10);
    dto.setStartDate(Instant.parse("2025-06-10T09:00:00Z"));
    dto.setEndDate(Instant.parse("2025-06-10T10:30:00Z"));
    dto.setCoachId(5L);
    dto.setCourseTypeId(2L);
    dto.setAgeRangeId(1L);
    return dto;
  }

  @BeforeEach
  void stubService() {
    // On n'atteindra normalement pas le contrôleur dans le test 403,
    // mais on met un stub safe pour éviter NPE si jamais.
    when(courseService.addCourse(any())).thenReturn(new Course());
  }

  /**
   * Token "valide" (subject extrait), mais l'utilisateur n'a PAS le rôle ADMIN.
   * Le contrôleur est protégé par @IsAdmin => AccessDenied => 403.
   */
  @Test
  void addCourse_returns403_withNonAdminToken() throws Exception {
    UserDetails nonAdmin = User.withUsername("user@example.com")
        .password("x")
        .roles("USER")
        .build();
    when(userDetailsService.loadUserByUsername("user@example.com")).thenReturn(nonAdmin);

    mockMvc.perform(post("/course")
                        .header("Authorization", "Bearer fake-token-not-admin")
                        .contentType("application/json")
                        .content(toJson(validDto())))
        .andExpect(status().isForbidden());
  }
}
