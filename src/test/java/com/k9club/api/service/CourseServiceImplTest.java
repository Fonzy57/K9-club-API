package com.k9club.api.service;

import com.k9club.api.dao.AgeRangeDao;
import com.k9club.api.dao.CourseDao;
import com.k9club.api.dao.CourseTypeDao;
import com.k9club.api.dao.UserDao;
import com.k9club.api.dto.course.CourseCreateDto;
import com.k9club.api.model.AgeRange;
import com.k9club.api.model.Course;
import com.k9club.api.model.CourseType;
import com.k9club.api.model.User;
import com.k9club.api.model.enums.UserRole;
import com.k9club.api.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseControllerTest {

  private CourseDao courseDao;
  private CourseTypeDao courseTypeDao;
  private AgeRangeDao ageRangeDao;
  private UserDao userDao;

  private CourseService service;

  // TODO POUR LES TESTS VOIR PAGE 554 DU PDF SI JAMAIS JE VEUX PLUS D'INFORMATIONS

  @BeforeEach
  void setUp() {
    courseDao = mock(CourseDao.class);
    courseTypeDao = mock(CourseTypeDao.class);
    ageRangeDao = mock(AgeRangeDao.class);
    userDao = mock(UserDao.class);
    service = new CourseServiceImpl(courseDao, courseTypeDao, ageRangeDao, userDao);
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

  private void mockValidLookups() {
    User coach = new User();
    coach.setId(5L);
    coach.setUserRole(UserRole.COACH);

    when(userDao.findByIdAndUserRole(5L, UserRole.COACH)).thenReturn(Optional.of(coach));
    when(courseTypeDao.findById(2L)).thenReturn(Optional.of(new CourseType()));
    when(ageRangeDao.findById(1L)).thenReturn(Optional.of(new AgeRange()));
    when(courseDao.save(any(Course.class))).thenAnswer(inv -> inv.getArgument(0));
  }

  @Test
  void addCourse_throws400_whenStartDateAfterEndDate() {
    CourseCreateDto dto = validDto();
    dto.setStartDate(Instant.parse("2025-06-10T12:00:00Z"));
    dto.setEndDate(Instant.parse("2025-06-10T10:00:00Z"));

    ResponseStatusException ex = assertThrows(
        ResponseStatusException.class,
        () -> service.addCourse(dto)
    );

    assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    assertNotNull(ex.getReason());
    assertTrue(ex.getReason().contains("date de début après date de fin"));
    verifyNoInteractions(courseDao);
  }

  @Test
  void addCourse_throws404_whenCoachNotFoundOrWrongRole() {
    CourseCreateDto dto = validDto();
    when(userDao.findByIdAndUserRole(5L, UserRole.COACH)).thenReturn(Optional.empty());

    ResponseStatusException ex = assertThrows(
        ResponseStatusException.class,
        () -> service.addCourse(dto)
    );

    assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    assertTrue(ex.getReason().contains("Coach"));
    verifyNoInteractions(courseDao);
  }

  @Test
  void addCourse_throws404_whenCourseTypeNotFound() {
    CourseCreateDto dto = validDto();

    User coach = new User();
    coach.setId(5L);
    coach.setUserRole(UserRole.COACH);
    when(userDao.findByIdAndUserRole(5L, UserRole.COACH)).thenReturn(Optional.of(coach));
    when(courseTypeDao.findById(2L)).thenReturn(Optional.empty());

    ResponseStatusException ex = assertThrows(
        ResponseStatusException.class,
        () -> service.addCourse(dto)
    );

    assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    assertTrue(ex.getReason().contains("Type de cours"));
    verifyNoInteractions(courseDao);
  }

  @Test
  void addCourse_throws404_whenAgeRangeNotFound() {
    CourseCreateDto dto = validDto();

    User coach = new User();
    coach.setId(5L);
    coach.setUserRole(UserRole.COACH);
    when(userDao.findByIdAndUserRole(5L, UserRole.COACH)).thenReturn(Optional.of(coach));
    when(courseTypeDao.findById(2L)).thenReturn(Optional.of(new CourseType()));
    when(ageRangeDao.findById(1L)).thenReturn(Optional.empty());

    ResponseStatusException ex = assertThrows(
        ResponseStatusException.class,
        () -> service.addCourse(dto)
    );

    assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    assertTrue(ex.getReason().contains("Tranche d'âge"));
    verifyNoInteractions(courseDao);
  }

  @Test
  void addCourse_savesAndReturnsCourse_whenValid() {
    CourseCreateDto dto = validDto();
    mockValidLookups();

    Course result = service.addCourse(dto);

    assertNotNull(result);
    assertEquals("Initiation Agility", result.getName());
    assertEquals(10, result.getMaxParticipants());
    verify(courseDao, times(1)).save(any(Course.class));
  }
}
