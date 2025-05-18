package com.k9club.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for validation errors.
 * <p>
 * Catches {@link MethodArgumentNotValidException} thrown when
 * request body validation fails and returns a structured
 * HTTP 400 Bad Request response containing field-specific error messages.
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

  // TODO AJOUTER DES MESSAGE POUR TOUT LES VALIDS DANS LES MODELS
  // COMME CA LE MESSAGE D'ERREUR POUR LE FRONT SERA CLAIR

  /**
   * Handles validation failures on controller method arguments.
   * <p>
   * Extracts field errors from the exception, maps each field name
   * to its validation message, and returns a JSON body with HTTP 400 status.
   *
   * @param exception the {@link MethodArgumentNotValidException} containing validation errors
   * @return a {@link ResponseEntity} with status 400 and a body containing:
   * <ul>
   *   <li><strong>status</strong>: the HTTP status code (400)</li>
   *   <li><strong>errors</strong>: a map of field names to error messages</li>
   * </ul>
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
    Map<String, String> fieldErrors = new HashMap<>();

    for (FieldError error : exception.getBindingResult().getFieldErrors()) {
      fieldErrors.put(error.getField(), error.getDefaultMessage());
    }

    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("status", HttpStatus.BAD_REQUEST.value());
    responseBody.put("errors", fieldErrors);

    return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
  }
}
