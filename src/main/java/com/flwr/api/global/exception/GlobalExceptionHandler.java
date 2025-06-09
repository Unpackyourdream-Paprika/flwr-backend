package com.flwr.api.global.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<Map<String, Object>> handleIllegalState(IllegalStateException ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("success", false);
    error.put("message", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("success", false);
    error.put("message", ex.getMessage());
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Map<String, Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
    Map<String, Object> error = new HashMap<>();
    error.put("success", false);
    error.put("message", "Invalid URL");
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error); // 405
  }
}
