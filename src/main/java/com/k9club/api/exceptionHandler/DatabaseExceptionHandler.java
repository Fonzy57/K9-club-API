package com.k9club.api.exceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for database-related errors.
 * <p>
 * Intercepts {@link DataIntegrityViolationException} thrown by JPA repositories
 * and translates them into HTTP 409 Conflict responses with a structured JSON body.
 */
@RestControllerAdvice
public class DatabaseExceptionHandler {

  // TODO REVOIR POUR FAIRE PEUT ÊTRE UNE CONDITION POUR CHECKER SI 409 OU AUTRE ERREUR

  /**
   * Handles database integrity violations, such as unique constraint failures.
   * <p>
   * When a {@link DataIntegrityViolationException} is thrown, responds with
   * HTTP 409 Conflict and a JSON body containing:
   * <ul>
   *   <li><strong>status</strong>: the HTTP status code (409)</li>
   *   <li><strong>error</strong>: a descriptive error message</li>
   * </ul>
   *
   * @param exception the exception thrown when a data integrity constraint is violated
   * @return a {@link ResponseEntity} with status 409 and a map containing error details
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("status", HttpStatus.CONFLICT.value());
    responseBody.put("error", "Donnée déjà existante : une contrainte d'unicité a été violée.");

    return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
  }
}
