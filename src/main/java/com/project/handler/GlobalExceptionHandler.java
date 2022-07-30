package com.project.handler;

import com.project.exception.InvalidDataException;
import com.project.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public ResponseEntity<String> handleResourceNotFoundException(HttpServletRequest request, Exception exception) {
      request.getHeader("Resource not found!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("status - data not found: " + exception.getMessage());
   }

   @ExceptionHandler(InvalidDataException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ResponseEntity<String> handleInvalidDataException(HttpServletRequest request, Exception exception) {
      request.getHeader("Invalid data!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("status - invalid passed data found: " + exception.getMessage());
   }
}
