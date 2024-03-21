package org.example.Handler;

import org.example.Exceptions.SellerFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SellerExceptionHandler {

  @ExceptionHandler(SellerFormatException.class)
  public ResponseEntity<String> handleSellerFormatException(
      SellerFormatException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}