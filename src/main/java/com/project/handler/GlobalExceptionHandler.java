package com.project.handler;

import com.project.exception.InvalidDataException;
import com.project.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<String> handleResourceNotFoundException(HttpServletRequest request, Exception exception) {
      request.getHeader("Resource not found!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("status - data not found: " + exception.getMessage());
   }

   @ExceptionHandler(InvalidDataException.class)
   public ResponseEntity<String> handleInvalidDataException(HttpServletRequest request, Exception exception) {
      request.getHeader("Invalid data!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("status - invalid passed data found: " + exception.getMessage());
   }

   @ExceptionHandler(ConversionFailedException.class)
   public ResponseEntity<String> handleConversionFailedException(Exception exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("status - conversion conflict: " + exception.getMessage());
   }
}
