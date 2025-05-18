package com.k9club.api.exceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for authentication and persistence-related errors.
 * <p>
 * Intercepts exceptions thrown by controllers (for example, unique constraint
 * violations) and translates them into standardized HTTP responses with
 * a plain text error message.
 * </p>
 */
@RestControllerAdvice
public class AuthExceptionHandler {

  /**
   * Handles DataIntegrityViolationException, which is typically thrown when
   * a database integrity constraint is violated (for example, attempting to
   * insert a duplicate email).
   * <p>
   * Returns a 409 Conflict status code and the error message in the response body.
   * </p>
   *
   * @param exception the DataIntegrityViolationException that was thrown
   * @return a ResponseEntity containing HTTP 409 status and the error message
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> handleDuplicateEmail(DataIntegrityViolationException exception) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body("Cet email est déjà utilisé.");
  }

}