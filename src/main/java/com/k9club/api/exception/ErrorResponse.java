package com.k9club.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Generic structure for API error responses.
 * <p>
 * This class represents a standardized payload to convey
 * error details (status and message) in REST API responses.
 * Fields that are null will be omitted from the serialized JSON
 * thanks to the @JsonInclude(Include.NON_NULL) annotation.
 */
// Ici on utilise @JsonInclude(Include.NON_NULL) pour ne pas sérialiser les champs à null si vous en ajoutez d’autres
// plus tard.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  /**
   * A short code or identifier representing the error.
   */
  private String status;

  /**
   * A human-readable message describing the error.
   */
  private String message;

  /**
   * Default constructor.
   * <p>
   * Required for frameworks that instantiate this class via reflection.
   */
  public ErrorResponse() {
  }

  /**
   * Creates an ErrorResponse with the given status and message.
   *
   * @param status  a short code or identifier for the error
   * @param message a human-readable description of the error
   */
  public ErrorResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  /**
   * Returns the error status code or identifier.
   *
   * @return the status of the error
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the error status code or identifier.
   *
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Returns the human-readable error message.
   *
   * @return the detail message of the error
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the human-readable error message.
   *
   * @param message the detail message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
