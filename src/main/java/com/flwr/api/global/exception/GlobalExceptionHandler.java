package com.flwr.api.global.exception;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  // Invalid JSON
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    log.warn("Invalid JSON: {}", ex.getMessage());

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", "Invalid JSON format.");
    return body;
  }

  // @Valid Failed
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {
    List<Map<String, Object>> errorDetails = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> {
              Map<String, Object> err = new HashMap<>();
              err.put("field", error.getField());
              err.put("rejectedValue", error.getRejectedValue());
              err.put("message", error.getDefaultMessage());
              return err;
            }).toList();

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", "Validation Failed.");
    body.put("errors", errorDetails);
    return body;
  }

  // Ill State
  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleIllegalState(IllegalStateException ex) {
    log.warn("IllegalStateException: {}", ex.getMessage());

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", ex.getMessage());
    return body;
  }

  // Ill Args
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleIllegalArgument(IllegalArgumentException ex) {
    log.warn("IllegalArgumentException: {}", ex.getMessage());

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", ex.getMessage());
    return body;
  }

  // HTTP Method now allowed
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public Map<String, Object> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
    log.warn("MethodNotSupported: {}", ex.getMethod());

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", "Invalid HTTP method.");
    return body;
  }

  // Spring Security
  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public Map<String, Object> handleAccessDenied(AccessDeniedException ex) {
    log.warn("AccessDenied: {}", ex.getMessage());

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", "Access Denied.");
    return body;
  }

  // JWT
  @ExceptionHandler(JwtException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Map<String, Object> handleJwtException(JwtException ex) {
    log.warn("JwtException: {}", ex.getMessage());

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", "Invalid or expired token.");
    return body;
  }

  // Runtime
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handleRuntimeException(RuntimeException ex) {
    log.error("Unexpected error: ", ex);

    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("message", "Unexpected server error occurred.");
    return body;
  }
}
