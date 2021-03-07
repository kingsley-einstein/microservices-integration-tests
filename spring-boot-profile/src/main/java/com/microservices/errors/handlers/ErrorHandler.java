package com.microservices.errors.handlers;

import com.microservices.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(value = Error.class)
  public ResponseEntity<String> error(Error error) {
    return new ResponseEntity<>(
      error.getMessage(),
      HttpStatus.valueOf(error.getStatusCode())
    );
  }
}
